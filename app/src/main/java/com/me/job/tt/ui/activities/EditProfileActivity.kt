package com.me.job.tt.ui.activities


class EditProfileActivity : BaseActivity() {
/*
    private lateinit var foundationInfoViewModel: FoundationInfoViewModel
     var foundationProfileBasicInfo:FoundationProfileBasicInfo?=null
    var foundationNameUpdateRequest: FoundNameUpdateRequest?=null
    var foundationWebsiteUpdateReq:FoundWebsiteUpdateRequest?=null

    //to test sharedReference
    //need to delete for this activity
    companion object {
        fun newIntent(
            context: Context
        ): Intent {
            val intent = Intent(context, EditProfileActivity::class.java)
            return intent
        }
    }

   //after back button of basic information activity
   override fun onRestart() {
   super.onRestart()
     getFoundationInfo(FoundationInfoRequest("2422ae8f-90fd-4f7c-b2e7-76155deb3720","103764197686640050053"))
     observe()
  }

  override fun onCreate(savedInstanceState: Bundle?) {
   super.onCreate(savedInstanceState)
   setContentView(R.layout.activity_edit_profile)

    //initiate the foundation view model to call method
   foundationInfoViewModel=ViewModelProviders.of(this@EditProfileActivity).get(FoundationInfoViewModel::class.java)
   getFoundationInfo(FoundationInfoRequest("2422ae8f-90fd-4f7c-b2e7-76155deb3720","103764197686640050053"))
   observe()

   //edit image view function
   iv_edit_cover_photo.setOnClickListener {
       startActivity(Intent(this,EditCoverPhotoActivity::class.java))

   }

    //back pressed
   iv_toolbar_back_arrow.setOnClickListener {
       onBackPressed()
   }

      //basic info edit button
   btn_basic_info_edit.setOnClickListener {

       val i = Intent(this, BasicInformationActivity::class.java)
       i.putExtra("myBasicInfo",foundationProfileBasicInfo)
       startActivity(i)

   }

      //foundation name click event
   tv_name_value.setOnClickListener {

       val i = Intent(this, ProfileNameEditActivity::class.java)
       i.putExtra("foundNameUpdateRequest",foundationNameUpdateRequest)
       startActivity(i)
   }

      //foundation website link click event
   tv_website_link_value.setOnClickListener {

       val i = Intent(this, WebsiteEditActivity::class.java)
       i.putExtra("foudWebsiteUpdateRequest",foundationWebsiteUpdateReq)
       startActivity(i)
   }

      //to test shareRef that need to delete for this activity
      tv_toolbar_title_edit_prof.setOnClickListener{

          Toast.makeText(this,"click event",Toast.LENGTH_SHORT).show()
          startActivity(Intent(this,SharedRefTestActivity::class.java))
      }

}


//get foundation response from view model
private fun getFoundationInfo(foundationInfoRequest:FoundationInfoRequest) {
   foundationInfoViewModel.getFoundationInfoResponse(foundationInfoRequest)
}

//observe the response from view model
private fun observe() {

    //to test sharedRef
    var dataList=ArrayList<String>()

    //response of viewmodel
   foundationInfoViewModel.foundationResponse.observe(this,Observer{
       updateUI(it)

       foundationProfileBasicInfo= FoundationProfileBasicInfo(
           it.data.id,
           it.data.foundation_email,
           it.data.foundation_phoneno,
           it.data.postalcode,
           it.data.location,
           it.data.history)

       //to test sharedRef
       dataList.add(it.data.id)
       dataList.add(it.data.foundation_email)
       dataList.add(it.data.foundation_phoneno)
       //save data to sharedRef
       ConfigUtils.initConfigUtils(this)
       ConfigUtils.getInstance().setFoundationProfileBasficInfo(dataList)

       //for websiteUpdate
       foundationWebsiteUpdateReq= FoundWebsiteUpdateRequest(it.data.id,
       it.data.website)

       //for name update
       foundationNameUpdateRequest= FoundNameUpdateRequest(it.data.id,it.data.name)

   })

    //error of viewmodel
   foundationInfoViewModel.mErrorLD.observe(this, Observer {
       showPromptDialog(it)
   })
}

    //update ui after calling view model method
private fun updateUI(response: FoundationDataResponse) {
   tv_name_value.text = response.data.name
   tv_email_value.text = response.data.foundation_email
   tv_phone_value.text = response.data.foundation_phoneno
   tv_postal_code_value.text=response.data.postalcode
   tv_location_value.text=response.data.location
   tv_website_link_value.text=response.data.website
   tv_foundation_history_value.text=response.data.history


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
}*/
}