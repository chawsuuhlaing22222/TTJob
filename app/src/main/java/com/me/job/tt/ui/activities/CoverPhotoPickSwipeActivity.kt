package com.me.job.tt.ui.activities

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.me.job.R
import com.me.job.tt.data.remote.request.FoundCoverPhotoUpdateRequest
import com.me.job.tt.data.remote.request.UpdatePhoto
import com.me.job.tt.ui.SmartScrollListener
import com.me.job.tt.ui.adapters.PickCoverPhotoAdapter
import com.me.job.tt.ui.adapters.PickCoverPhotoSwipeAdapter
import com.me.job.tt.ui.delegate.PickCoverPhotoDelegate
import com.me.job.tt.utils.ConfigUtils
import com.me.job.tt.viewModels.FoundationInfoViewModel
import kotlinx.android.synthetic.main.activity_cover_photo_pick_swipe.*

import java.io.ByteArrayOutputStream

class CoverPhotoPickSwipeActivity : BaseActivity(), PickCoverPhotoDelegate, SmartScrollListener.OnSmartScrollListener {

    lateinit var foundationInfoViewModel: FoundationInfoViewModel
    lateinit var mPickCoverPhotoAdapter: PickCoverPhotoSwipeAdapter
    lateinit var mFoundationCoverPhotoResponse: FoundCoverPhotoUpdateRequest

    //to set ok btn visible or not
    var selectedItemCount:Int=0
    //code to get permission from phone gallery data
    private val REQUEST_PICK_FILE_MM =2003
    private val REQUEST_SINGLE_PICK_IMAGE=2004
    //to send request
    var count=0
    private var photoList= arrayListOf<String?>()
    var selectedPhotoList:ArrayList<String> = arrayListOf()
    lateinit var requestPhotoList:ArrayList<UpdatePhoto>
    // var uploadPhotoList:ArrayList<UpdatePhoto>?=null

    //for scrollistener
    private var isLoad: Boolean = false
    private var visibleItemCount = 0
    private var pastVisibleItems = 0
    private var totalItemCount = 0
    private var currentDy = 0
    private var previousDy = 0

    //for selected
    private var selected=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cover_photo_pick_swipe)

        ConfigUtils.initConfigUtils(this)
        //get cover photo list
        //getCoverPhoto("2422ae8f-90fd-4f7c-b2e7-76155deb3720",1,10)
        upadteUI()
        loadFeeds("1")
        observe()
        observeupdate()

        //action of btn_new click
       /* btn_new.setOnClickListener {
            pickFromFile()

        }*/

        btn_ok.setOnClickListener {
            var myData= Intent()
            myData.putStringArrayListExtra("mydata",selectedPhotoList)
            setResult(RESULT_OK, myData)
            finish()

        }

        iv_menu.setOnClickListener {

            var popupMenu = PopupMenu(this,it)
            popupMenu.menuInflater.inflate(R.menu.pick_coverphoto_menu,popupMenu.menu)
            popupMenu.setOnMenuItemClickListener{ it->
                when(it.itemId){
                    R.id.btn_new->{
                        selected=0
                        pickFromFile()
                    }

                    R.id.btn_select->{
                        allowSelect()
                    }
                }

                true

            }
            popupMenu.show()

        }

    }


    fun upadteUI(){

        //initialize view model
        foundationInfoViewModel= ViewModelProviders.of(this).get(FoundationInfoViewModel::class.java)
        selectedPhotoList.clear()
        requestPhotoList= ArrayList<UpdatePhoto>()

        //recyclerview init
        mPickCoverPhotoAdapter= PickCoverPhotoSwipeAdapter(this,this)
        rv_pick_photo.adapter=mPickCoverPhotoAdapter
        rv_pick_photo.layoutManager= GridLayoutManager(this,2)
        rv_pick_photo.setHasFixedSize(true)

        initScrollListener()
        swipeRefresh.setOnRefreshListener {
            loadFeeds("1")
        }

    }

    fun allowSelect(){
        selected=1
        selectedPhotoList.clear()
        mPickCoverPhotoAdapter.setData(photoList,selected)

    }

    //get photo from api
    fun getCoverPhoto(fId:String,page:Int,count:Int){
        foundationInfoViewModel.getCoverPhotoList(fId,page,count)
    }

    //observe
    fun observe(){
        foundationInfoViewModel.mFoundCoverPhotoListResonse.observe(this,{
            swipeRefresh.isRefreshing = false
            photoList.remove(null)
            if (it.success) {

                //if data exits
                if(it.data.isNotEmpty()){
                    if (it.paging?.page == 1) {
                        photoList = arrayListOf()
                        photoList.addAll(it.data)
                    } else photoList.addAll(it.data)

                    mPickCoverPhotoAdapter.setData(photoList,selected)
                    isLoad = false

                }else { //no data
                    mPickCoverPhotoAdapter.setData(photoList,selected)
                    //mCurrencyHistoryAdapter
                    isLoad = false
                    Toast.makeText(this, "no more data", Toast.LENGTH_SHORT).show()
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
            if(data?.clipData != null){   //for multiple images selected

                var count: Int = data?.clipData.itemCount//.getItemCount()

                for (i in 0 until count){
                    //taking selected data uri
                    val imageUri: Uri = data?.clipData.getItemAt(i).uri
                    var extension=contentResolver.getType(imageUri)
                    var imageName=imageUri.pathSegments.last()
                    Log.i("filename",imageName)
                    Log.i("fileext",extension)

                    var imgBitmap: Bitmap =
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


            }else if(data?.data != null){    //for single image selected

                val selectedImageUri: Uri = data?.data
                var extension=contentResolver.getType(selectedImageUri)
                var imageName=selectedImageUri.pathSegments.last()

                //changing uri to bitmap
                var imgBitmap: Bitmap =
                    MediaStore.Images.Media.getBitmap(contentResolver, selectedImageUri)

                var img_base64=encodeImage(imgBitmap)

                requestPhotoList.add(UpdatePhoto(imageName,img_base64,extension))
                /*getFoundationCoverPhotoUpdateResponse(
                    FoundCoverPhotoUpdateRequest(
                        "2422ae8f-90fd-4f7c-b2e7-76155deb3720",
                        requestPhotoList))*/
                //  observeupdate()

            }

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
            Toast.makeText(this,"imported img size ${it.data.size}", Toast.LENGTH_SHORT).show()

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
        Toast.makeText(this,"View Image",Toast.LENGTH_SHORT).show()
    }

    override fun onSelectPhoto(data:String) {
        selectedItemCount++
        btn_ok.visibility= View.VISIBLE
        selectedPhotoList.add(data)

    }

    override fun onUnselectPhoto(data:String) {
        selectedItemCount--
        if(selectedItemCount==0){
            btn_ok.visibility= View.GONE
        }
        selectedPhotoList.remove(data)
    }


    //scrolllistener
    private fun initScrollListener() {
        var isListEndReached = false
        rv_pick_photo.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(rv: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(rv, dx, dy)

                currentDy = dy
                if (currentDy > previousDy) {
                } else if (currentDy < previousDy) {
                    isListEndReached = false
                }

                visibleItemCount = rv_pick_photo.layoutManager!!.childCount
                totalItemCount = rv_pick_photo.layoutManager!!.itemCount
                pastVisibleItems =
                    (rv_pick_photo.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                previousDy = currentDy
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, scrollState: Int) {
                super.onScrollStateChanged(recyclerView, scrollState)

                if (!isLoad) {
                    if (scrollState == RecyclerView.SCROLL_STATE_IDLE) {
                        if ((visibleItemCount + pastVisibleItems) >= totalItemCount && !isListEndReached) {
                            isListEndReached = true
                            loadMore()
                            isLoad = true
                        }

                    }
                }
            }
        })
    }

    private fun loadFeeds(pageNo: String) {
        swipeRefresh.isRefreshing = true
        ConfigUtils.getInstance().savePageNo(pageNo.toInt())
        var page:Int=pageNo.toInt()
        getCoverPhoto("2422ae8f-90fd-4f7c-b2e7-76155deb3720",page,10)
        //"1447622565390640"
    }

    //if list end ,call this method
    private fun loadMore() {
        photoList.add(null)
        mPickCoverPhotoAdapter.notifyItemInserted(photoList.size - 1)
        ConfigUtils.getInstance().savePageNo(
            ConfigUtils.getInstance().loadPageNo() + 1
        )

        //need to change view model method for purchase history
        Handler().postDelayed({
            getCoverPhoto("2422ae8f-90fd-4f7c-b2e7-76155deb3720",ConfigUtils.getInstance().loadPageNo(),10)
        }, 0)
    }

    override fun onListEndReach() {
        TODO("Not yet implemented")
    }

}