package com.me.job.tt.ui.activities

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.me.job.R
import com.me.job.tt.data.remote.request.FoundCoverPhotoUpdateRequest
import com.me.job.tt.data.remote.request.UpdatePhoto
import com.me.job.tt.data.remote.response.CoverPhotoUpdateDataResponse
import com.me.job.tt.data.remote.response.FoundProfCoverPhoUpdateResponse
import com.me.job.tt.ui.adapters.PickCoverPhotoAdapter
import com.me.job.tt.ui.delegate.PickCoverPhotoDelegate
import com.me.job.tt.viewModels.FoundationInfoViewModel
import kotlinx.android.synthetic.main.activity_cover_photo_pick.*
import java.io.ByteArrayOutputStream


class CoverPhotoPickActivity : BaseActivity(),PickCoverPhotoDelegate {

    lateinit var foundationInfoViewModel:FoundationInfoViewModel
    lateinit var mPickCoverPhotoAdapter:PickCoverPhotoAdapter
    lateinit var mFoundationCoverPhotoResponse:FoundCoverPhotoUpdateRequest
   //to set ok btn visible or not
    var selectedItemCount:Int=0
    //code to get permission from phone gallery data
    private val REQUEST_PICK_FILE_MM =2003
    private val REQUEST_SINGLE_PICK_IMAGE=2004
    //to send request
    var count=0
    lateinit var photoList:ArrayList<String>
    var selectedPhotoList:ArrayList<String> = arrayListOf()
   lateinit var requestPhotoList:ArrayList<UpdatePhoto>
   // var uploadPhotoList:ArrayList<UpdatePhoto>?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cover_photo_pick)

        //initialize view model
        foundationInfoViewModel= ViewModelProviders.of(this).get(FoundationInfoViewModel::class.java)

        selectedPhotoList.clear()
        requestPhotoList= ArrayList<UpdatePhoto>()
        photoList= ArrayList<String>()
        //get cover photo list
        getCoverPhoto("2422ae8f-90fd-4f7c-b2e7-76155deb3720",1,10)

        //recyclerview init
        mPickCoverPhotoAdapter= PickCoverPhotoAdapter(this,this)
        rv_pick_photo.adapter=mPickCoverPhotoAdapter
        rv_pick_photo.layoutManager=GridLayoutManager(this,2)
        observe()
        observeupdate()
        //action of btn_new click
        btn_new.setOnClickListener {
              pickFromFile()

        }

        btn_ok.setOnClickListener {

            var myData=Intent()
            myData.putStringArrayListExtra("mydata",selectedPhotoList)
            setResult(RESULT_OK, myData)
            finish()

        }


    }

    //get photo from api
    fun getCoverPhoto(fId:String,page:Int,count:Int){
        foundationInfoViewModel.getCoverPhotoList(fId,page,count)
    }

    //observe
   fun observe(){
        foundationInfoViewModel.mFoundCoverPhotoListResonse.observe(this,{

            if(it.success){
                it.data.let {

                    Log.i("photo",it.size.toString())
                    photoList.addAll(it)
                    mPickCoverPhotoAdapter.setNewData(photoList as MutableList<String>)
                }
            }
        })
       foundationInfoViewModel.mErrorLD.observe(this,{
           showPromptDialog(it)
       })
    }

    /*
    all are from editcoverphotouploadconstraint
     */
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
        startActivityForResult(Intent.createChooser(intent, "Select images"),REQUEST_PICK_FILE_MM)


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        requestPhotoList=ArrayList<UpdatePhoto>()

        if (requestCode === REQUEST_PICK_FILE_MM && resultCode === RESULT_OK){

             if(data?.data != null){    //for single image selected

                val selectedImageUri: Uri = data?.data
                var extension=contentResolver.getType(selectedImageUri)
                var imageName=selectedImageUri.pathSegments.last()

                //changing uri to bitmap
                var imgBitmap:Bitmap=
                    MediaStore.Images.Media.getBitmap(contentResolver, selectedImageUri)

                var img_base64=encodeImage(imgBitmap)

                requestPhotoList.add(UpdatePhoto(imageName,img_base64,extension))
                /*getFoundationCoverPhotoUpdateResponse(
                    FoundCoverPhotoUpdateRequest(
                        "2422ae8f-90fd-4f7c-b2e7-76155deb3720",
                        requestPhotoList))*/
                //  observeupdate()

            }else if(data?.clipData != null){   //for multiple images selected

                var count: Int = data?.clipData.itemCount//.getItemCount()

                    for (i in 0 until count){
                        //taking selected data uri
                        val imageUri: Uri = data?.clipData.getItemAt(i).uri
                        var extension=contentResolver.getType(imageUri)
                        var imageName=imageUri.pathSegments.last()
                        Log.i("filename",imageName)
                        Log.i("fileext",extension)

                        var imgBitmap:Bitmap=
                            MediaStore.Images.Media.getBitmap(contentResolver, imageUri)

                        //bitmap to base 64
                        //changing bitmap to url
                        var img_base64=encodeImage(imgBitmap)

                        //assigning bitmap to coverPhoto that is add arraylist
                        requestPhotoList.add(UpdatePhoto(imageName,img_base64,extension))
                    }
                        /*getFoundationCoverPhotoUpdateResponse(
                            FoundCoverPhotoUpdateRequest(
                                "2422ae8f-90fd-4f7c-b2e7-76155deb3720",
                                    requestPhotoList))*/


                }

            for(i in requestPhotoList){
                Log.i("imgname",i.photo_name)
                Log.i("ext",i.photo_extension)
            }
            getFoundationCoverPhotoUpdateResponse(
                FoundCoverPhotoUpdateRequest(
                    "2422ae8f-90fd-4f7c-b2e7-76155deb3720",
                    requestPhotoList))
        }


    }
    //get foundation response from view model
    private fun getFoundationCoverPhotoUpdateResponse(foundationCoverPhotoUpdateRequest: FoundCoverPhotoUpdateRequest) {


        for(i in foundationCoverPhotoUpdateRequest.photo_list){
            Log.i("m",i.photo_base64)
            //Log.i("b",i.photo_base64)
        }

        Log.i("s",foundationCoverPhotoUpdateRequest.photo_list.size.toString())
        showLoading()
        foundationInfoViewModel.updateFoProfileCoverPhoto(foundationCoverPhotoUpdateRequest)

    }

    //observe the response from view model
    private fun observeupdate() {

        foundationInfoViewModel.mFoundCoverPhotoListResonseNew.observe(this, Observer{
                        Toast.makeText(this,"imported img size ${it.data.size}",Toast.LENGTH_SHORT).show()

                Log.i("imported","1")
                updateUi(it.data as ArrayList<String>)


        })
        //error of viewmodel
        foundationInfoViewModel.mErrorLD.observe(this, Observer {
            showPromptDialog(it)
        })
    }

    fun updateUi(l:ArrayList<String>){
        for(i in l){
            Log.i("l",i)
        }
        photoList.addAll(0,l)
        mPickCoverPhotoAdapter.notifyItemRangeInserted(0,l.size)
       // mPickCoverPhotoAdapter.notifyDataSetChanged()
        hideLoading()
    }

  /*  //update ui after calling view model method
    private fun updateUI(response: FoundProfCoverPhoUpdateResponse) {

        //initiate empty cover photos
        responseCoverPhotoList= ArrayList<CoverPhotoUpdateDataResponse>()
        responseCoverPhotoList.addAll(response.data!!)
        /*if(response.data?.size!!<4){

            var count=4-(response.data.size)
            for(i in 0 until count){
                responseCoverPhotoList.add(CoverPhotoUpdateDataResponse("img1",
                    "https://amhantayar-dev.sgp1.digitaloceanspaces.com/Question/2422ae8f-90fd-4f7c-b2e7-76155deb3720_8222021052756AM_fp.jpg"))
            }


        }*/
        // adapter.setNewData(responseCoverPhotoList)
        //adapter = ConstraintCoverPhotoAdapter(this, responseCoverPhotoList)
        //rvCoverPhotos.adapter=adapter

    }*/

    private fun encodeImage(bm: Bitmap): String {
        val baos = ByteArrayOutputStream()
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val b = baos.toByteArray()
        return Base64.encodeToString(b, Base64.DEFAULT)
    }
    /*
    all are end from editcoverphotouploadconstraint
     */

    override fun onClickPhoto() {
        TODO("Not yet implemented")
    }

    override fun onSelectPhoto(data:String) {
        selectedItemCount++
        btn_ok.visibility=View.VISIBLE
        selectedPhotoList.add(data)

    }

    override fun onUnselectPhoto(data:String) {
        selectedItemCount--
        if(selectedItemCount==0){
            btn_ok.visibility=View.GONE
        }
        selectedPhotoList.remove(data)
    }
}