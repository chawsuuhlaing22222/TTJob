package com.me.job.tt.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.me.job.R
import kotlinx.android.synthetic.main.activity_donation_name_edit.*
import android.text.method.ScrollingMovementMethod
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.me.job.tt.data.remote.request.FoundNameUpdateRequest
import com.me.job.tt.data.remote.request.FoundWebsiteUpdateRequest
import com.me.job.tt.data.remote.response.FoundationBasicInfoUpdateResponse
import com.me.job.tt.viewModels.FoundationInfoViewModel
import kotlinx.android.synthetic.main.activity_basic_information.*
import kotlinx.android.synthetic.main.activity_donation_name_edit.iv_toolbar_back_arrow
import kotlinx.android.synthetic.main.activity_donation_name_edit.tv_notice_content
import kotlinx.android.synthetic.main.activity_website_edit.*


class ProfileNameEditActivity : BaseActivity() {

    private lateinit var foundationInfoViewModel: FoundationInfoViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_donation_name_edit)

        //initiate the view model
        foundationInfoViewModel= ViewModelProviders.of(this@ProfileNameEditActivity).get(FoundationInfoViewModel::class.java)

        //to get profile name from intent
        var profileNameUpdateRequest=intent.getSerializableExtra("foundNameUpdateRequest") as FoundNameUpdateRequest?

        //set name
        et_name.setText(profileNameUpdateRequest?.name)


        //to make textview do scroll
        tv_notice_content.movementMethod = ScrollingMovementMethod()

        //toolbar
        iv_toolbar_back_arrow.setOnClickListener{
            onBackPressed()
        }

        //toolbar save method
        tv_save_update_name.setOnClickListener {

            //checking foundation name is empty or not
            var foundationName=et_name.text.toString()
            if(foundationName.isEmpty()){
                Toast.makeText(this,"name must be filled", Toast.LENGTH_LONG).show()
            }

            //call local update method
            updateFoundationName(
                FoundNameUpdateRequest(
                    profileNameUpdateRequest!!.id,
                    foundationName
                )
            )
        }

    }

    //call view model function
    private fun updateFoundationName(foundationNameUpdateRequest: FoundNameUpdateRequest) {
        foundationInfoViewModel.updateFoProfileName(foundationNameUpdateRequest)
    }

    //observe the view model response
    private fun observe() {

        foundationInfoViewModel.foundationBasicInfoUpdateResponse.observe(this, Observer{
            updateUI(it)

        })
        foundationInfoViewModel.mErrorLD.observe(this, Observer {
            showPromptDialog(it)
        })
    }

    //update ui
    private fun updateUI(response: FoundationBasicInfoUpdateResponse) {

        et_name.setText(response.data?.name)
    }

     override fun showPromptDialog(errorMsg: String) {
        val alertDialog: AlertDialog? = this.let {
            val builder = AlertDialog.Builder(it)
            builder.apply {
                setPositiveButton(
                    "OK"
                ) { dialog, id ->
                    dialog.dismiss()
                }
            }
            builder.create()
        }
        alertDialog!!.setMessage(errorMsg)
        alertDialog.show()
    }
}