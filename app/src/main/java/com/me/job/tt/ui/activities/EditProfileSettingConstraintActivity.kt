package com.me.job.tt.ui.activities

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.me.job.BuildConfig.MAPS_API_KEY
import com.me.job.R
import com.me.job.tt.data.remote.request.FoundNameUpdateRequest
import com.me.job.tt.data.remote.request.FoundWebsiteUpdateRequest
import com.me.job.tt.data.remote.request.FoundationInfoRequest
import com.me.job.tt.data.remote.request.FoundationProfileBasicInfo
import com.me.job.tt.data.remote.response.FoundationDataResponse
import com.me.job.tt.ui.adapters.ImageSliderAdapter
import com.me.job.tt.utils.AppConstants
import com.me.job.tt.viewModels.FoundationInfoViewModel
import com.smarteist.autoimageslider.SliderView
import kotlinx.android.synthetic.main.activity_edit_profile.*
import kotlinx.android.synthetic.main.activity_edit_profile.btn_basic_info_edit
import kotlinx.android.synthetic.main.activity_edit_profile.iv_edit_cover_photo
import kotlinx.android.synthetic.main.activity_edit_profile.iv_toolbar_back_arrow
import kotlinx.android.synthetic.main.activity_edit_profile.tv_website_link_value
import kotlinx.android.synthetic.main.activity_edit_profile_setting_constraint.*

class EditProfileSettingConstraintActivity : BaseActivity(),
    GoogleMap.OnMyLocationButtonClickListener, ActivityCompat.OnRequestPermissionsResultCallback,
    GoogleMap.OnMyLocationClickListener,OnMapReadyCallback {

    private lateinit var foundationInfoViewModel: FoundationInfoViewModel
    var foundationProfileBasicInfo: FoundationProfileBasicInfo?=null
    var foundationNameUpdateRequest: FoundNameUpdateRequest?=null
    var foundationWebsiteUpdateReq: FoundWebsiteUpdateRequest?=null
    //location
    //lateinit var location:String
    lateinit var mapview:MapView
    // var myImageUriList:ArrayList<String> //to test image slider

    //after back button of basic information activity
    override fun onRestart() {
        super.onRestart()
        getFoundationInfo(FoundationInfoRequest("2422ae8f-90fd-4f7c-b2e7-76155deb3720","103764197686640050053"))
        observe()
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile_setting_constraint)

        //mapview
        var bundle:Bundle?=null
        mapview=findViewById(R.id.map_google_map)
        if(savedInstanceState!=null){
            bundle = savedInstanceState.getBundle(MAPS_API_KEY)
        }
        mapview.onCreate(bundle)
        mapview.getMapAsync(this)
        //initiate the foundation view model to call method
        foundationInfoViewModel= ViewModelProviders.of(this@EditProfileSettingConstraintActivity).get(FoundationInfoViewModel::class.java)
        getFoundationInfo(FoundationInfoRequest("2422ae8f-90fd-4f7c-b2e7-76155deb3720","103764197686640050053"))
        showLoading()
        observe()



        //edit image view function
        iv_edit_cover_photo.setOnClickListener {
           //]
             startActivity(Intent(this,EditCoverPhotoActivity::class.java))
           //  startActivity(Intent(this,EditCoverPhotoUploadConstraintActivity::class.java))
        }

        //back pressed
        iv_toolbar_back_arrow_edit_profile.setOnClickListener {
            onBackPressed()

        }

        //basic info edit button
        btn_basic_info_edit_constraint.setOnClickListener {

            val i = Intent(this, BasicInformationActivity::class.java)
            i.putExtra("myBasicInfo",foundationProfileBasicInfo)
            startActivity(i)

        }

        //foundation name click event
        tv_profile_name_value_constraint.setOnClickListener {

            val i = Intent(this, ProfileNameEditActivity::class.java)
            i.putExtra("foundNameUpdateRequest",foundationNameUpdateRequest)
            startActivity(i)
        }

        //foundation website link click event
        tv_website_link_value_constraint.setOnClickListener {

            val i = Intent(this, WebsiteEditActivity::class.java)
            i.putExtra("foudWebsiteUpdateRequest",foundationWebsiteUpdateReq)
            startActivity(i)
        }
    }


    //for goole map
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        var bundle:Bundle?=outState.getBundle(MAPS_API_KEY)
        if(bundle==null){
            bundle=Bundle()
            outState.putBundle(MAPS_API_KEY,bundle)

        }
        mapview.onSaveInstanceState(bundle)

    }

    override fun onStart() {
        super.onStart()
        mapview.onStart()
    }

    override fun onPause() {
        super.onPause()
        mapview.onPause()
    }

    override fun onResume() {
        super.onResume()
        mapview.onResume()
    }

    override fun onStop() {
        super.onStop()
        mapview.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapview.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapview.onLowMemory()
    }


      override fun onMapReady(googleMap: GoogleMap) {


        var mMap = googleMap

        // Add a marker in Sydney and move the camera
        val location = LatLng(16.844353, 96.128355)
        //val sydney = LatLng(-33.852, 151.211)
        mMap.addMarker(
            MarkerOptions().position(location)
                .title("Current Location")
                .icon(
                    BitmapDescriptorFactory
                    .defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
        )
        mMap.uiSettings.isZoomControlsEnabled=true
        mMap.uiSettings.isZoomGesturesEnabled=true
        mMap.uiSettings.isCompassEnabled=true
        mMap.uiSettings.isMapToolbarEnabled=true
        mMap.uiSettings.isScrollGesturesEnabled=true
        mMap.uiSettings.isTiltGesturesEnabled=true
        mMap.uiSettings.isRotateGesturesEnabled=true
          /* if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
               == PackageManager.PERMISSION_GRANTED) {
               mMap.isMyLocationEnabled = true
           } else {
               // Permission to access the location is missing. Show rationale and request permission
               requestPermission(this, LOCATION_PERMISSION_REQUEST_CODE,
                   Manifest.permission.ACCESS_FINE_LOCATION, true
               )
           }*/

        mMap.setOnMyLocationButtonClickListener(this)
        mMap.setOnMyLocationClickListener(this)

        //this is for zoom level and to appear marker
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 10.0f));
    }



    override fun onMyLocationClick(location: Location) {
        Toast.makeText(this, "Current location:\n$location", Toast.LENGTH_LONG)
            .show()
    }

    override fun onMyLocationButtonClick(): Boolean {
        Toast.makeText(this, "MyLocation button clicked", Toast.LENGTH_SHORT)
            .show()
        // Return false so that we don't consume the event and the default behavior still occurs
        // (the camera animates to the user's current position).
        return false
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }


    //end of google map


    //get foundation response from view model
    private fun getFoundationInfo(foundationInfoRequest:FoundationInfoRequest) {
        foundationInfoViewModel.getFoundationInfoResponse(foundationInfoRequest)
    }

    //observe the response from view model
    private fun observe() {

        foundationInfoViewModel.foundationResponse.observe(this, Observer{
            updateUI(it)
            it.data?.let {
                foundationProfileBasicInfo= FoundationProfileBasicInfo(
                    it.id,
                    it.foundation_email,
                    it.foundation_phoneno,
                    it.postalcode,
                    it.location,
                    it.history)

                foundationWebsiteUpdateReq= FoundWebsiteUpdateRequest(it.id,
                    it.website)

                foundationNameUpdateRequest= FoundNameUpdateRequest(it.id,it.name)

            }
          /*  foundationProfileBasicInfo= FoundationProfileBasicInfo(
                it.data.id,
                it.data.foundation_email,
                it.data.foundation_phoneno,
                it.data.postalcode,
                it.data.location,
                it.data.history)


            foundationWebsiteUpdateReq= FoundWebsiteUpdateRequest(it.data.id,
                it.data.website)

            foundationNameUpdateRequest= FoundNameUpdateRequest(it.data.id,it.data.name)*/

            //to test image slider
            it.data?.let {
                var myImageUriList=it.foundation_photos as ArrayList<String>
                Log.d("photos",myImageUriList.size.toString())
                setImageInSlider(myImageUriList)
            }

           /*var myImageUriList=it.data.foundation_photos as ArrayList<String>
            Log.d("photos",myImageUriList.size.toString())
            setImageInSlider(myImageUriList)*/
            hideLoading()
        })
        foundationInfoViewModel.mErrorLD.observe(this, Observer {
            showPromptDialog(it)
            Log.i("apkm",it)
        })
    }

    //update ui after calling view model method
    private fun updateUI(response: FoundationDataResponse) {

        response.data?.let {

            tv_profile_name_value_constraint.text = it.name
            tv_email_value_constraint.text = it.foundation_email
            tv_phone_value_constraint.text = it.foundation_phoneno
            tv_postalcode_value_constraint.text=it.postalcode
            tv_location_value_constraint.text=it.location
            tv_website_link_value_constraint.text=it.website
            tv_foundation_history_value_constraint.text=it.history

            //mapview
            //location=it.

            //AIzaSyBw2ULcqop1_ADRQzui5goiZQSR3y8HG-4

        }
       /* tv_profile_name_value_constraint.text = response.data.name
        tv_email_value_constraint.text = response.data.foundation_email
        tv_phone_value_constraint.text = response.data.foundation_phoneno
        tv_postalcode_value_constraint.text=response.data.postalcode
        tv_location_value_constraint.text=response.data.location
        tv_website_link_value_constraint.text=response.data.website
        tv_foundation_history_value_constraint.text=response.data.history*/


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

   //slider test
    private fun setImageInSlider(images: ArrayList<String>) {
        val adapter = ImageSliderAdapter()
        adapter.renewItems(images)
        sl_cover_photo_list.setSliderAdapter(adapter)
       sl_cover_photo_list.isAutoCycle = true
       sl_cover_photo_list.startAutoCycle()
    }


}