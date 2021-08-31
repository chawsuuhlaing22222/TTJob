package com.me.job.tt.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.me.job.R
import com.me.job.tt.data.remote.request.FoundWebsiteUpdateRequest
import com.me.job.tt.data.remote.request.FoundationProfileBasicInfo
import com.me.job.tt.data.remote.request.FoundationProfileBasicInfoUpdateRequest
import com.me.job.tt.data.remote.response.FoundationBasicInfoUpdateResponse
import com.me.job.tt.viewModels.FoundationInfoViewModel
import kotlinx.android.synthetic.main.activity_basic_information.*
import kotlinx.android.synthetic.main.activity_website_edit.*

class WebsiteEditActivity : BaseActivity() {

    private lateinit var foundationInfoViewModel: FoundationInfoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_website_edit)

        //initiate the view model
        foundationInfoViewModel= ViewModelProviders.of(this@WebsiteEditActivity).get(FoundationInfoViewModel::class.java)

        //fill text field with the data from edit profile activity
        var websiteUpdateRequest=intent.getSerializableExtra("foudWebsiteUpdateRequest") as FoundWebsiteUpdateRequest?
        et_link.setText(websiteUpdateRequest?.website)

        //toolbar
        iv_toolbar_back_arrow.setOnClickListener {
            onBackPressed()
        }

        //make text view scrollable
        tv_notice_content.movementMethod = ScrollingMovementMethod()

        //toolbar save click method
        tv_save_update_website.setOnClickListener {

            //checking foundation website is empty or not
            var foundationWebsite= et_link.text.toString()
            if(foundationWebsite.isEmpty()){
                Toast.makeText(this,"Website must be filled",Toast.LENGTH_LONG).show()
            }

            updateFoundationWebsiteLink(
                FoundWebsiteUpdateRequest(
                    "2422ae8f-90fd-4f7c-b2e7-76155deb3720",
                    foundationWebsite))
            observe()
        }
    }

    //call view model function
    private fun updateFoundationWebsiteLink(foundationWebsiteUpdateRequest: FoundWebsiteUpdateRequest) {
        foundationInfoViewModel.updateFoProfileWebsite(foundationWebsiteUpdateRequest)
    }

    //observe the view model response
    private fun observe() {
        foundationInfoViewModel.foundationBasicInfoUpdateResponse.observe(this, Observer{
           if(it.message!="success"){
               showPromptDialog(it.message)
           }

            updateUI(it)

        })
        foundationInfoViewModel.mErrorLD.observe(this, Observer {
            showPromptDialog(it)

        })
    }

    //update ui
    private fun updateUI(response: FoundationBasicInfoUpdateResponse) {

       et_link.setText(response.data?.website)
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