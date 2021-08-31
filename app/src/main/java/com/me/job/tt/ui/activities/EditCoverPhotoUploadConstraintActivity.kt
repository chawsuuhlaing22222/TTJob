package com.me.job.tt.ui.activities

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.widget.GridView
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.me.job.R
import com.me.job.tt.data.remote.request.*
import com.me.job.tt.data.remote.response.CoverPhotoUpdateDataResponse
import com.me.job.tt.data.remote.response.FoundProfCoverPhoUpdateResponse
import com.me.job.tt.ui.adapters.ConstraintCoverPhotoAdapter
import com.me.job.tt.viewModels.FoundationInfoViewModel
import kotlinx.android.synthetic.main.activity_edit_cover_photo.*
import kotlinx.android.synthetic.main.activity_edit_cover_photo.tv_upload
import kotlinx.android.synthetic.main.activity_edit_cover_photo_upload_constraint.*
import kotlinx.android.synthetic.main.activity_edit_profile.*
import java.io.ByteArrayOutputStream

class EditCoverPhotoUploadConstraintActivity : BaseActivity() {

    private lateinit var foundationInfoViewModel: FoundationInfoViewModel
    var adapter: ConstraintCoverPhotoAdapter? = null
    private val REQUEST_PICK_FILE = 2003
    private val REQUEST_SINGLE_PICK_IMAGE=2004
    //to get response
    lateinit var responseCoverPhotoList : ArrayList<CoverPhotoUpdateDataResponse>
    //to send request
    lateinit var requestPhotoList:ArrayList<UpdatePhoto>
    lateinit var deleteCoverPhoto: CoverPhotoUpdateDataResponse
    var changedLoc:Int=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_cover_photo_upload_constraint)


        //initiate the foundation view model to call method
        foundationInfoViewModel= ViewModelProviders.of(this@EditCoverPhotoUploadConstraintActivity).get(FoundationInfoViewModel::class.java)

        //toolbar
        iv_toolbar_back_arrow_edit_cover.setOnClickListener {
            onBackPressed()
        }


        //initiate empty cover photos
        //to get response
        responseCoverPhotoList= ArrayList<CoverPhotoUpdateDataResponse>()



        //default image from url
        var bImg= BitmapFactory.decodeResource(resources,R.drawable.empty)
        responseCoverPhotoList.add(CoverPhotoUpdateDataResponse("img1","https://amhantayar-dev.sgp1.digitaloceanspaces.com/Question/2422ae8f-90fd-4f7c-b2e7-76155deb3720_8222021052756AM_fp.jpg"))
        responseCoverPhotoList.add(CoverPhotoUpdateDataResponse("img2","https://amhantayar-dev.sgp1.digitaloceanspaces.com/Question/2422ae8f-90fd-4f7c-b2e7-76155deb3720_8222021052756AM_fp.jpg"))
        responseCoverPhotoList.add(CoverPhotoUpdateDataResponse("img3","https://amhantayar-dev.sgp1.digitaloceanspaces.com/Question/2422ae8f-90fd-4f7c-b2e7-76155deb3720_8222021052756AM_fp.jpg"))
        responseCoverPhotoList.add(CoverPhotoUpdateDataResponse("img4","https://amhantayar-dev.sgp1.digitaloceanspaces.com/Question/2422ae8f-90fd-4f7c-b2e7-76155deb3720_8222021052756AM_fp.jpg"))
        adapter = ConstraintCoverPhotoAdapter(this, responseCoverPhotoList)
        gvCoverPhotos_edit_cover.adapter=adapter

        //pick btn method
        btn_pick_cover_photo_constraint.setOnClickListener{

            //initiate the var   //everytime pick images coverphotolist is empty
            //coverPhotoList= ArrayList<CoverPhotoUpdateDataResponse>()
            requestPhotoList= ArrayList<UpdatePhoto>()
            pickFromFile()

        }


        //tv_upload method
        tv_upload.setOnClickListener{
            //it
        }


        //gridview click action
        var gridViewCoverPhoto: GridView =findViewById(R.id.gvCoverPhotos_edit_cover)
        gridViewCoverPhoto.setOnItemClickListener { adapterView, view, i, l ->

            //one image view click
            changedLoc=i
            deleteCoverPhoto= responseCoverPhotoList[i]
            pickSingleImg()


        }

    }

    //pick single image from gallery
    private fun pickSingleImg() {

        val intent: Intent = Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        startActivityForResult(Intent.createChooser(intent, "Select images"),REQUEST_SINGLE_PICK_IMAGE)
    }


    //pick multiple image from gallery
    private fun pickFromFile() {

        val intent: Intent = Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select images"),REQUEST_PICK_FILE)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        //this is for single image selected
        if (requestCode === REQUEST_SINGLE_PICK_IMAGE && resultCode === RESULT_OK) {
            if (data?.data != null) {    //for single image selected

                //taking selected data uri
                val selectedImageUri: Uri = data?.data
                var extension=contentResolver.getType(selectedImageUri)
                var imageName=selectedImageUri.pathSegments.last()
                Log.i("selectedimg",selectedImageUri.toString())


                //changing uri to bitmap
                var imgBitmap:Bitmap=
                    MediaStore.Images.Media.getBitmap(contentResolver, selectedImageUri)

                //bitmap to base 64
                //changing bitmap to url
                var img_base64=encodeImage(imgBitmap)

                //removing data at changedLoc
                requestPhotoList.removeAt(changedLoc)
                requestPhotoList.add(changedLoc,UpdatePhoto(imageName,img_base64,extension))

                getFoundationCoverPhotoUpdateResponse(FoundCoverPhotoUpdateRequest("2422ae8f-90fd-4f7c-b2e7-76155deb3720",requestPhotoList))
                observe()


            }
        }




        //this is for multiple imgs selected
        if (requestCode === REQUEST_PICK_FILE && resultCode === RESULT_OK) {

            if (data?.clipData != null) {    //for multiple images selected
                var count: Int = data?.clipData.itemCount//.getItemCount()

                //constraint the count (max is 4)
                if(count>=4)count =4

                //getting images from content
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

                getFoundationCoverPhotoUpdateResponse(FoundCoverPhotoUpdateRequest("2422ae8f-90fd-4f7c-b2e7-76155deb3720",requestPhotoList))
                observe()

            } else if (data?.data != null) {    //for single image selected

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


        }



    }


    private fun encodeImage(bm: Bitmap): String {
        val baos = ByteArrayOutputStream()
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val b = baos.toByteArray()
        return Base64.encodeToString(b, Base64.DEFAULT)
    }

    //get foundation response from view model
    private fun getFoundationCoverPhotoUpdateResponse(foundationCoverPhotoUpdateRequest: FoundCoverPhotoUpdateRequest) {
        foundationInfoViewModel.updateFoProfileCoverPhoto(foundationCoverPhotoUpdateRequest)
    }

    //observe the response from view model
    private fun observe() {

        //response of viewmodel
        foundationInfoViewModel.foundCoverPhotoUpdateResponse.observe(this, Observer{
            updateUI(it)

        })

        //error of viewmodel
        foundationInfoViewModel.mErrorLD.observe(this, Observer {
            showPromptDialog(it)
        })
    }

    //update ui after calling view model method
    private fun updateUI(response: FoundProfCoverPhoUpdateResponse) {

        //initiate empty cover photos
        responseCoverPhotoList= ArrayList<CoverPhotoUpdateDataResponse>()
        responseCoverPhotoList.addAll(response.data!!)
       if(response.data?.size!!<4){

           var count=4-(response.data.size)
           for(i in 0 until count){
               responseCoverPhotoList.add(CoverPhotoUpdateDataResponse("img1",
                   "https://amhantayar-dev.sgp1.digitaloceanspaces.com/Question/2422ae8f-90fd-4f7c-b2e7-76155deb3720_8222021052756AM_fp.jpg"))
           }


       }
        adapter = ConstraintCoverPhotoAdapter(this, responseCoverPhotoList)
        gvCoverPhotos_edit_cover.adapter=adapter

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