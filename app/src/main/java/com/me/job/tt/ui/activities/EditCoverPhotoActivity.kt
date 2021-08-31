package com.me.job.tt.ui.activities

import android.content.Intent
import android.os.Bundle
import com.me.job.R
import com.me.job.tt.data.vos.CoverPhoto
import com.me.job.tt.ui.adapters.FCoverPhotoAdapter
import kotlinx.android.synthetic.main.activity_edit_cover_photo.*
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import android.widget.GridView
import androidx.lifecycle.ViewModelProviders
import com.me.job.tt.viewModels.FoundationInfoViewModel
import kotlinx.android.synthetic.main.image_entry.*
import kotlinx.android.synthetic.main.image_entry.view.*


class EditCoverPhotoActivity : BaseActivity() {

    private lateinit var foundationInfoViewModel: FoundationInfoViewModel
        var adapter: FCoverPhotoAdapter? = null
        private val REQUEST_PICK_FILE = 2003
        private val REQUEST_SINGLE_PICK_IMAGE=2004
        lateinit var coverPhotoList : ArrayList<CoverPhoto>
        lateinit var singleImgBitmap:Bitmap
        var changedLoc:Int=0

    //lateinit var selectedImg:ArrayList<Bitmap>

    override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_edit_cover_photo)

        //initiate the foundation view model to call method
        foundationInfoViewModel= ViewModelProviders.of(this@EditCoverPhotoActivity).get(FoundationInfoViewModel::class.java)

            //toolbar
            iv_toolbar_back_arrow.setOnClickListener {
                onBackPressed()
            }


            //initiate empty cover photoswe`,   klio
            coverPhotoList= ArrayList<CoverPhoto>()

            //default image
            var bImg=BitmapFactory.decodeResource(resources,R.drawable.empty)
            coverPhotoList.add(CoverPhoto((bImg)))
            coverPhotoList.add(CoverPhoto((bImg)))
            coverPhotoList.add(CoverPhoto((bImg)))
            coverPhotoList.add(CoverPhoto((bImg)))
            adapter = FCoverPhotoAdapter(this, coverPhotoList)
            gvCoverPhotos.adapter=adapter


            //pick btn method
            btn_pick_cover_photo.setOnClickListener{

                //initiate the var   //everytime pick images coverphotolist is empty
                coverPhotoList= ArrayList<CoverPhoto>()
                pickFromFile()

            }


            //tv_upload method
            tv_upload.setOnClickListener{
                //it
            }


        //gridview click action
            var gridViewCoverPhoto:GridView=findViewById(R.id.gvCoverPhotos)
            gridViewCoverPhoto.setOnItemClickListener { adapterView, view, i, l ->

                //one image view click
               // view.iv_cover_photo.setOnClickListener {
                    changedLoc=i
                    singleImgBitmap= coverPhotoList[i].image
                    pickSingleImg()
               // }

/*
                view.ib_delete.setOnClickListener {
                    coverPhotoList.removeAt(i)

                    adapter = CoverPhotoAdapter(this, coverPhotoList)
                    gvCoverPhotos.adapter=adapter
                }*/





               /* view.ib_delete.setOnClickListener {
                    coverPhotoList.removeAt(i)
                }*/

                // loop through the adapter view items
                //these codes are background color changes.eg,if one image is selected,change bg of this,
                //others bg are normal
               /* for (index in 0 until adapterView.count){
                    adapterView[index].apply {


                        /*if (index != i){
                            // background color for not selected items
                            val itemBackgroundColor=Color.parseColor("#FFFFFF")
                           cv_cover_photo.setBackgroundColor(itemBackgroundColor)
                            cv_cover_photo.setCardBackgroundColor(itemBackgroundColor)
                        }else{
                            // background color for selected item
                            val selectedItemBackgroundColor=Color.parseColor("#ADD8E6")
                            cv_cover_photo.setBackgroundColor(selectedItemBackgroundColor)
                            cv_cover_photo.setCardBackgroundColor(selectedItemBackgroundColor)
                        }*/


                    }
                }*/
            }


    }

    //pick single image from gallery
    private fun pickSingleImg() {

        val intent:Intent = Intent(Intent.ACTION_PICK,
            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
       // intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        startActivityForResult(Intent.createChooser(intent, "Select images"),REQUEST_SINGLE_PICK_IMAGE)
    }


    //pick multiple image from gallery
    private fun pickFromFile() {

         val intent:Intent = Intent(Intent.ACTION_PICK,
             android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
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

                Log.i("selectedimg",selectedImageUri.toString())


                //changing uri to bitmap
                var imgBitmap:Bitmap=
                    MediaStore.Images.Media.getBitmap(contentResolver, selectedImageUri)

                //assigning selected bitmap to adapter
                singleImgBitmap=imgBitmap
                coverPhotoList[changedLoc]= CoverPhoto(singleImgBitmap)
                adapter = FCoverPhotoAdapter(this, coverPhotoList)
                //adapter!!.notifyDataSetChanged()
                gvCoverPhotos.adapter=adapter


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

                    //assigning bitmap to coverPhoto that is add arraylist
                    coverPhotoList.add(CoverPhoto(imgBitmap))

                    //selectedImg.add(imgBitmap)
                }

                //assigning selected images to adapter
                /*adapter = CoverPhotoAdapter(this, coverPhotoList)
                gvCoverPhotos.adapter=adapter*/

            } else if (data?.data != null) {    //for single image selected

                //taking selected data uri
                val selectedImageUri: Uri = data?.data

                //changing uri to bitmap
                var imgBitmap:Bitmap=
                    MediaStore.Images.Media.getBitmap(contentResolver, selectedImageUri)

                //selectedImg.add(imgBitmap)

                //assigning bitmap to coverPhoto that is add arraylist
                coverPhotoList.add(CoverPhoto(imgBitmap))

                //assigning selected images to adapter
                /*adapter = CoverPhotoAdapter(this, coverPhotoList)
                gvCoverPhotos.adapter=adapter*/

            }

            //if imglist is less than 4,add empty image to list
            if(coverPhotoList.size<4){

               var count:Int=4-coverPhotoList.size
                for(i in 0 until count){

                    var bImg=BitmapFactory.decodeResource(resources,R.drawable.empty)
                    coverPhotoList.add(CoverPhoto((bImg)))
                }

            }
            //assigning selected images to adapter
            adapter = FCoverPhotoAdapter(this, coverPhotoList)
            gvCoverPhotos.adapter=adapter
        }



    }



}
     /*
        val pickPhoto = Intent(Intent.ACTION_PICK)
        pickPhoto.type = "image/*"
        pickPhoto.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        startActivityForResult(pickPhoto, REQUEST_PICK_FILE)
     */
   */



