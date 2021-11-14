package com.me.job.tt.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.me.job.R
import com.me.job.tt.utils.ConfigUtils
import com.sendbird.android.*
import com.sendbird.android.SendBird.ConnectHandler
import com.sendbird.android.SendBird.DisconnectHandler
import kotlinx.android.synthetic.main.activity_chat_uikitactivity.*


class ChatUIKITActivity : BaseActivity() {

    // Retrieving all users
    var listQuery = SendBird.createApplicationUserListQuery()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_uikitactivity)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val backArrow =
            ContextCompat.getDrawable(applicationContext, R.drawable.ic_baseline_arrow_back_24)
        supportActionBar!!.setHomeAsUpIndicator(backArrow)


        var userids=mutableListOf<String>()
        userids.add("jobuser1")

        var nickName=mutableListOf<String>()
        nickName.add("chaw1")


        listQuery.setUserIdsFilter(userids)
        getAllUsers()


    }

    fun login(userId: String?, handler: ConnectHandler?) {
        SendBird.connect(
            userId
        ) { user, e -> handler?.onConnected(user, e) }
    }

    fun logout(handler: DisconnectHandler?) {
        SendBird.disconnect { handler?.onDisconnected() }
    }

    fun addConnectionManagementHandler(handlerId: String?, handler: ConnectionManagementHandler?) {
        SendBird.addConnectionHandler(handlerId, object : SendBird.ConnectionHandler {
            override fun onReconnectStarted() {}
            override fun onReconnectSucceeded() {
                handler?.onConnected(true)
            }

            override fun onReconnectFailed() {}
        })
        if (SendBird.getConnectionState() == SendBird.ConnectionState.OPEN) {
            handler?.onConnected(false)
        } else if (SendBird.getConnectionState() == SendBird.ConnectionState.CLOSED) { // push notification or system kill
            val userId: String = ConfigUtils.getInstance().loadUserId()
            SendBird.connect(userId, ConnectHandler { user, e ->
                if (e != null) {
                    return@ConnectHandler
                }
                handler?.onConnected(false)
            })
        }
    }

    fun removeConnectionManagementHandler(handlerId: String?) {
        SendBird.removeConnectionHandler(handlerId)
    }

    interface ConnectionManagementHandler {
        /**
         * A callback for when connected or reconnected to refresh.
         *
         * @param reconnect Set false if connected, true if reconnected.
         */
        fun onConnected(reconnect: Boolean)
    }

    fun getAllUsers(){
        listQuery.next(object: UserListQuery.UserListQueryResultHandler {
            override fun onResult(users_list: MutableList<User>?, p1: SendBirdException?) {

                Toast.makeText(this@ChatUIKITActivity,"user list size is "+users_list?.size,Toast.LENGTH_SHORT).show()
                tv_user_count.text=users_list?.size.toString()
            }


            // A list of users is successfully retrieved.
                // Through the "list" parameter of the onResult() callback method,
                // you can access the data of each user from the result list that Sendbird server has passed to the onResult().


        });
    }

    fun blockUser(user:User){
        SendBird.blockUser(user, object: SendBird.UserBlockHandler {

             override fun onBlocked(user:User, e:SendBirdException) {
                if (e != null) {
                    // Handle error.
                }

                // The blocked user can be retrieved through the "user" parameter of the onBlocked() callback method.

            }
        });
    }

    fun unBlockUser(user:User){
        SendBird.unblockUser(user, object: SendBird.UserUnblockHandler{
            override fun onUnblocked(e:SendBirdException) {
                if (e != null) {
                    // Handle error.
                }

                // The user is successfully unblocked.

            }
        });
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return super.onOptionsItemSelected(item)
        when(item?.itemId){

        }
    }


}