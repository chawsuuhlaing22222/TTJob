package com.me.job.tt.ui.activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.me.job.R
import com.me.job.tt.utils.ConfigUtils
import kotlinx.android.synthetic.main.activity_basic_information.*
import android.content.Intent
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.me.job.tt.data.remote.request.FoundationInfoRequest
import com.me.job.tt.data.remote.request.FoundationProfileBasicInfo
import com.me.job.tt.data.remote.request.FoundationProfileBasicInfoUpdateRequest
import com.me.job.tt.data.remote.response.FoundationBasicInfoUpdateResponse
import com.me.job.tt.data.remote.response.FoundationDataResponse
import com.me.job.tt.viewModels.FoundationInfoViewModel
import kotlinx.android.synthetic.main.activity_edit_profile.*
import kotlinx.android.synthetic.main.activity_edit_profile_setting_constraint.*



class BasicInformationActivity : BaseActivity() {



    private lateinit var foundationInfoViewModel: FoundationInfoViewModel
   private var basicInfo: FoundationProfileBasicInfo?=null
    init {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basic_information)

        //initinate view model to call the viewmodel functions
        foundationInfoViewModel= ViewModelProviders.of(this@BasicInformationActivity).get(FoundationInfoViewModel::class.java)

        //fill the text field with the values from intent(that from EditProfile Activity)
        val intent = intent
        basicInfo= intent.getSerializableExtra("myBasicInfo") as FoundationProfileBasicInfo?
        et_email.setText(basicInfo?.foundationEmail)
        et_phone.setText(basicInfo?.foundationPhone)
        et_postal_code.setText(basicInfo?.foundationPostalCode)
        et_location.setText(basicInfo?.foundationLocation)
        et_foundation_history.setText(basicInfo?.foundationHistory)


        //toolbar back arrow
        iv_toolbar_back_arrow_basic_info.setOnClickListener {
            onBackPressed()

        }

        //toolbar save function
        tv_save.setOnClickListener {

            //checking foundation email is empty or not
            var foundationEmail=et_email.text
            if(foundationEmail.isEmpty()){
                Toast.makeText(this,"email must be filled",Toast.LENGTH_LONG).show()
            }

            //checking foundation phone no is empty or not
            var foundationPhone=et_phone.text
            if(foundationPhone.isEmpty()){
                Toast.makeText(this,"phone must be filled",Toast.LENGTH_LONG).show()
            }

            //checking foundation history is empty or not
            var foundationHistory=et_foundation_history.text
            if(foundationHistory.isEmpty()){
                Toast.makeText(this,"foundation history must be filled",Toast.LENGTH_LONG).show()
            }


            //checking foundation location is empty or not
            var foundationLocation=et_location.text
            if(foundationLocation.isEmpty()){
                Toast.makeText(this,"foundation location must be filled",Toast.LENGTH_LONG).show()
            }

            //checking foundation postalcode is empty or not
            var foundationPostalcode=et_postal_code.text
            if(foundationPostalcode.isEmpty()){
                Toast.makeText(this,"foundation Postalcode must be filled",Toast.LENGTH_LONG).show()
            }

            //local update method call
            updateFoundationBasicInfo(
                FoundationProfileBasicInfoUpdateRequest(
                   basicInfo!!.id,
                   foundationEmail.toString(),// basicInfo!!.foundationEmail,
                   foundationPhone.toString(), //basicInfo!!.foundationPhone,
                    foundationPostalcode.toString(),
                    foundationLocation.toString(),
                    foundationHistory.toString()))

            //local observe method call
            observe()
        }
    }

    //update method of viewmodel is called
    private fun updateFoundationBasicInfo(foundationInfoUpdateRequest: FoundationProfileBasicInfoUpdateRequest) {
        foundationInfoViewModel.updateFoProfileBasicInfo(foundationInfoUpdateRequest)
    }

    //observe the viewmodel response
    private fun observe() {
        foundationInfoViewModel.foundationBasicInfoUpdateResponse.observe(this, Observer{

            updateUI(it)
            if(it.message!="success"){
                // Toast.makeText(this,"Pending state",Toast.LENGTH_SHORT).show()
                showPromptDialog(it.message)
            }
        })

        foundationInfoViewModel.mErrorLD.observe(this, Observer {
            showPromptDialog(it)

        })
    }

    //after calling update mehtod, update the ui
    private fun updateUI(response: FoundationBasicInfoUpdateResponse) {

        response.data?.let {
            et_email.setText(it.foundation_email)
            et_phone.setText(it.foundation_phoneno)
            et_postal_code.setText(it.postalcode)
            et_location.setText(it.location)
            et_foundation_history.setText(it.history)
        }

        /*et_email.setText(response.data.foundation_email)
        et_phone.setText(response.data.foundation_phoneno)
        et_postal_code.setText(response.data.postalcode)
        et_location.setText(response.data.location)
        et_foundation_history.setText(response.data.history)*/

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