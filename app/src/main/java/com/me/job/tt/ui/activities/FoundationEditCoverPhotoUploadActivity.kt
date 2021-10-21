package com.me.job.tt.ui.activities

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.text.TextUtils
import android.util.Base64
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.me.job.R
import com.me.job.tt.data.remote.request.*
import com.me.job.tt.data.remote.response.CoverPhotoUpdateDataResponse
import com.me.job.tt.ui.adapters.ImageSliderAdapter
import com.me.job.tt.ui.adapters.ShowCoverPhotoAdapter
import com.me.job.tt.ui.delegate.EditCoverPhotoDelegate
import com.me.job.tt.ui.delegate.ShowCoverPhotoDelegate
import com.me.job.tt.viewModels.FoundationInfoViewModel
import kotlinx.android.synthetic.main.activity_edit_cover_photo.*
import kotlinx.android.synthetic.main.activity_edit_cover_photo.tv_upload
import kotlinx.android.synthetic.main.activity_edit_cover_photo_upload_constraint.*
import kotlinx.android.synthetic.main.activity_edit_cover_photo_upload_constraint.sl_cover_photo_list
import kotlinx.android.synthetic.main.activity_edit_profile.*
import kotlinx.android.synthetic.main.activity_edit_profile_setting_constraint.*
import java.io.ByteArrayOutputStream

class FoundationEditCoverPhotoUploadActivity : BaseActivity(), EditCoverPhotoDelegate,ShowCoverPhotoDelegate {

    private lateinit var foundationInfoViewModel: FoundationInfoViewModel
   // lateinit var adapter: ConstraintCoverPhotoAdapter
    lateinit var showCoverPhotoAdapter:ShowCoverPhotoAdapter
    //private val REQUEST_PICK_FILE = 2003
    //private val REQUEST_SINGLE_PICK_IMAGE=2004
    private val REQUEST_PICK_ACCOUNT=2005
    lateinit var themeCoverPhotoUpdateRequest: ThemeCoverPhotoUpdateRequest
    //to get response
    lateinit var responseCoverPhotoList : ArrayList<CoverPhotoUpdateDataResponse>
    //to send request
    var requestPhotoList:ArrayList<UpdatePhoto> = arrayListOf()
    var themeCoverPhotoList:ArrayList<String> = arrayListOf()
    lateinit var deleteCoverPhoto: CoverPhotoUpdateDataResponse
    var changedLoc:Int=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_cover_photo_upload_constraint)
        txtThemeDescription_setting.isSelected=true
        txtThemeDescription_setting.ellipsize= TextUtils.TruncateAt.MARQUEE

        themeCoverPhotoUpdateRequest= intent.getSerializableExtra("themeobject") as ThemeCoverPhotoUpdateRequest

        et_description_con.setText(themeCoverPhotoUpdateRequest.theme.toString())
        txtThemeDescription_setting.text=themeCoverPhotoUpdateRequest.theme.toString()
        setImageInSlider(themeCoverPhotoUpdateRequest.photo_list as ArrayList<String>)



        //initiate the foundation view model to call method
        foundationInfoViewModel= ViewModelProviders.of(this@FoundationEditCoverPhotoUploadActivity).get(FoundationInfoViewModel::class.java)

        //toolbar
        iv_toolbar_back_arrow_edit_cover.setOnClickListener {
            onBackPressed()
        }


        //adapter = ConstraintCoverPhotoAdapter(this, this)

        showCoverPhotoAdapter= ShowCoverPhotoAdapter(this,this)
        rvCoverPhotos.adapter=showCoverPhotoAdapter
        rvCoverPhotos.layoutManager=GridLayoutManager(this,2)
        rvCoverPhotos.setHasFixedSize(true)
        showCoverPhotoAdapter.setNewData(themeCoverPhotoUpdateRequest.photo_list as ArrayList<String>)


      //pick btn method
       btn_pick_cover_photo_constraint.setOnClickListener{

           pickFromAccount()
       }



     tv_upload.setOnClickListener {

         foundationInfoViewModel.updateThemeCoverPhoto(
             ThemeCoverPhotoUpdateRequest(
                 "2422ae8f-90fd-4f7c-b2e7-76155deb3720",
                 "103764197686640050053",
                et_description_con.text.toString(),
                 themeCoverPhotoList
             ))


         //response of viewmodel
         foundationInfoViewModel.mThemeCoverPhotoUpdateResponse.observe(this, Observer{
             //updateUI(it)
             if(it.success){
                 Toast.makeText(this,it.data!!.foundation_photos.size.toString(),Toast.LENGTH_SHORT).show()
                 it.data.let {

                     txtThemeDescription_setting.text=it.theme
                     setImageInSlider(it.foundation_photos as ArrayList<String>)
                 }

             }
             //theme.setNewData(it.data as MutableList<CoverPhotoUpdateDataResponse>)

         })

         //error of viewmodel
         foundationInfoViewModel.mErrorLD.observe(this, Observer {
             showPromptDialog(it)
         })
     }


    }

    //pick multiple image from account
    private fun pickFromAccount() {

        val intent: Intent = Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        intent.action = Intent.ACTION_GET_CONTENT
        //startActivityForResult(Intent(this,CoverPhotoPickActivity::class.java),REQUEST_PICK_ACCOUNT)
        startActivityForResult(Intent(this,CoverPhotoPickSwipeActivity::class.java),REQUEST_PICK_ACCOUNT)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        //this is for account image selected
        if (requestCode === REQUEST_PICK_ACCOUNT && resultCode === RESULT_OK) {
            requestPhotoList.clear()
                var imgList:ArrayList<String>? = data?.getStringArrayListExtra("mydata") ?:null

            if (imgList != null) {
                Toast.makeText(this,imgList.size.toString(),Toast.LENGTH_SHORT).show()
                themeCoverPhotoList.clear()
                themeCoverPhotoList.addAll(imgList)
                showCoverPhotoAdapter.setNewData(imgList)
            }else{
                Toast.makeText(this,"Empty",Toast.LENGTH_SHORT).show()
            }

        }

        //this is for multiple imgs selected
       /* if (requestCode === REQUEST_PICK_FILE && resultCode === RESULT_OK) {
            requestPhotoList.clear()

            if (data?.clipData != null) {    //for multiple images selected
                var count: Int = data?.clipData.itemCount//.getItemCount()
                for (i in 0 until count) {

                    //taking selected data uri
                    val imageUri: Uri = data?.clipData.getItemAt(i).uri
                    var extension=contentResolver.getType(imageUri)
                    var imageName=imageUri.pathSegments.last()
                    Log.i("filename",imageName)
                    Log.i("fileext",extension)

                    //changing uri to bitmap
                    var imgBitmap:Bitmap=
                        MediaStore.Images.Media.getBitmap(contentResolver, imageUri)

                    //bitmap to base 64
                    //changing bitmap to url
                    var img_base64=encodeImage(imgBitmap)

                    //assigning bitmap to coverPhoto that is add arraylist
                    requestPhotoList.add(UpdatePhoto(imageName,img_base64,extension))
                    //coverPhotoList.add(CoverPhoto(imgBitmap))

                    //selectedImg.add(imgBitmap)
                }



            } else if (data?.data != null) {    //for single image selected
                requestPhotoList.clear()
                //taking selected data uri
                val selectedImageUri: Uri = data?.data
                var extension=contentResolver.getType(selectedImageUri)
                var imageName=selectedImageUri.pathSegments.last()

                //changing uri to bitmap
                var imgBitmap:Bitmap=
                    MediaStore.Images.Media.getBitmap(contentResolver, selectedImageUri)

                //bitmap to base 64
                //changing bitmap to url
                var img_base64=encodeImage(imgBitmap)

                //assigning bitmap to coverPhoto that is add arraylist
                requestPhotoList.add(UpdatePhoto(imageName,img_base64,extension))

            }


        }*/


    }


    private fun encodeImage(bm: Bitmap): String {
        val baos = ByteArrayOutputStream()
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val b = baos.toByteArray()
        return Base64.encodeToString(b, Base64.DEFAULT)
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


    override fun onCoverPhotoClick(data: CoverPhotoUpdateDataResponse) {
        Log.i("coverphoto",data.toString())
    }

    override fun coverPhotoClick(img: String) {
        //to delete cover photo
    }
}