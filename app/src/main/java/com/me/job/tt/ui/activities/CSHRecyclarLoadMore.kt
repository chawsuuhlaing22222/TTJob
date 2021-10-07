package com.me.job.tt.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.android.tintoung.ui.delegates.FoundationDonorsDelegate
import com.me.job.R
import com.me.job.tt.data.remote.response.FoundationDonors
import com.me.job.tt.ui.SmartScrollListener
import com.me.job.tt.ui.adapters.CSHLoadMoreAdapter
import com.me.job.tt.ui.adapters.FoundationDonorsAdapter
import com.me.job.tt.utils.ConfigUtils
import com.me.job.tt.viewModels.FoundationInfoViewModel
import kotlinx.android.synthetic.main.activity_cshrecyclar_load_more.*

class CSHRecyclarLoadMore : BaseActivity(), FoundationDonorsDelegate
  {



    //list for holding data //csh change
     var list: ArrayList<FoundationDonors.Data> = arrayListOf()

    //handler instance
    var handler: Handler = Handler()
    lateinit var mAdapter : FoundationDonorsAdapter


    lateinit var layoutManager1: LinearLayoutManager
      var isListEndReached = false
    private lateinit var mViewModel: FoundationInfoViewModel
    private var isLoad: Boolean = false
    private var visibleItemCount = 0
    private var pastVisibleItems = 0
    private var totalItemCount = 0
    private var currentDy = 0
    private var previousDy = 0
    private var currentPage = 1
    private var initPageCount = 5
    private var totalCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cshrecyclar_load_more)

        list.clear()
        setUpUI()
        getDonors("2422ae8f-90fd-4f7c-b2e7-76155deb3720", currentPage, initPageCount)
       // swipeRefresh.setOnRefreshListener(this)
        observe()
        isLoad = false
        initScrollListener()
    }

     private fun observe() {
         isLoad = true
        mViewModel.mFoundationDonorList.observe(this, Observer {
            if (it.success) {
                swipeRefresh.isRefreshing = false
                animationView.visibility = View.GONE
                //totalCount = it.paging.total_count

                if(it.data!=null){
                    list.addAll(it.data)
                    animationView.visibility = View.GONE
                    mAdapter.notifyDataSetChanged()
                }else{

                    isListEndReached=true
                }

               // isLoading = false
            }else {
                swipeRefresh.isRefreshing = false
                animationView.visibility = View.GONE
                showPromptDialog(it.message)
//                showPromptDialog(it.error.)
            }

        })
        mViewModel.mErrorLD.observe(this, Observer {
            swipeRefresh.isRefreshing = false
            animationView.visibility = View.GONE
            showPromptDialog(it)

        })
    }

  private fun setUpUI() {
       // supportActionBar?.title = getString(R.string.title_Donor)
      // supportActionBar?.setDisplayHomeAsUpEnabled(true)
        layoutManager1=LinearLayoutManager(this@CSHRecyclarLoadMore, RecyclerView.VERTICAL, false)
        mViewModel = ViewModelProviders.of(this).get(FoundationInfoViewModel::class.java)
        mAdapter = FoundationDonorsAdapter(this, this)
        rv_loadMore.apply {
            adapter = mAdapter
            layoutManager =layoutManager1


        }
      mAdapter.setNewData(list)
    }

    fun getDonors(foundationId: String, page: Int, pageCount: Int) {
        swipeRefresh.isRefreshing = true
        animationView.visibility = View.VISIBLE
        mViewModel.foundationDonorList(foundationId, page, pageCount)

    }



   override fun onItemClick(data: FoundationDonors.Data) {
       /* startActivity(
            ProfileActivity.newIntent(
                this, ConfigUtils.getInstance().loadUserId(),
                ConfigUtils.getInstance().loadAccessId(),
                data.userid
            )
        )*/
    }

    override fun onAccessRejectClick(data: FoundationDonors.Data) {
        TODO("Not yet implemented")
    }

    private fun initScrollListener() {

        rv_loadMore.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(rv: RecyclerView, dx: Int, dy: Int) {
                /*currentDy = dy
                if (currentDy > previousDy) {
                } else if (currentDy < previousDy) {
                    isListEndReached = false
                }
                visibleItemCount = rv.layoutManager!!.childCount
                totalItemCount = rv.layoutManager!!.itemCount
                pastVisibleItems =
                    (rv.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()

                previousDy = currentDy*/
                super.onScrolled(rv, dx, dy)

            }
            override fun onScrollStateChanged(recyclerView: RecyclerView, scrollState: Int) {
                super.onScrollStateChanged(recyclerView, scrollState)

                 if (!isLoad && !isListEndReached)
                {
                    //findLastCompletelyVisibleItemPostition() returns position of last fully visible view.
                    ////It checks, fully visible view is the last one.
                    if (layoutManager1.findLastCompletelyVisibleItemPosition() == list.size - 1)
                    {
                            loadMore()
                            isLoad=false

                    }
                }

                if(isListEndReached){
                    Toast.makeText(this@CSHRecyclarLoadMore,"End of list",Toast.LENGTH_SHORT).show()
                }


            }


        })

    }

    private fun loadMore() {
        handler.post(Runnable
        {
            //list.add("load")
            //mAdapter.notifyItemInserted(list.size - 1)
            animationView.visibility = View.VISIBLE
        })

        currentPage+=1
        Handler().postDelayed({
            mViewModel.foundationDonorList("foundationId", currentPage , 10 )
            observe()
        }, 225)


    }


}



/*All below is my test code
    class CSHRecyclarLoadMore : BaseActivity(){

        //list for holding data //csh change
        lateinit var list: ArrayList<String>

        //handler instance
        var handler: Handler = Handler()
        // lateinit var mAdapter : FoundationDonorsAdapter

        lateinit var mAdapter: CSHLoadMoreAdapter
        lateinit var layoutManager1: LinearLayoutManager

        private lateinit var mViewModel: FoundationInfoViewModel
        private var isLoad: Boolean = false
        private var visibleItemCount = 0
        private var pastVisibleItems = 0
        private var totalItemCount = 0
        private var currentDy = 0
        private var previousDy = 0
        private var currentPage = 1
        private var initPageCount = 10
        private var totalCount = 0

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_cshrecyclar_load_more)


            setUpUI()
            list = ArrayList()
            load()
            mAdapter.setNewDataList(list)
            animationView.visibility =View.GONE
            addScrollerListener()



            //initScrollListener()
            //getDonors("2422ae8f-90fd-4f7c-b2e7-76155deb3720", currentPage, initPageCount)
            // swipeRefresh.setOnRefreshListener(this)
            //observe()
        }
    //below code is mine
        fun loadMore() {
            //notify adapter using Handler.post() or RecyclerView.post()
            handler.post(Runnable
            {
                //list.add("load")
                //mAdapter.notifyItemInserted(list.size - 1)
                animationView.visibility = View.VISIBLE
            })
            handler.postDelayed(Runnable {
                //removes "load".
                //list.removeAt(list.size - 1)

                animationView.visibility =View.GONE
                var listSize = list.size
                mAdapter.notifyItemRemoved(listSize)
                //sets next limit
                var nextLimit = listSize + 10
                for (i in listSize until nextLimit) {
                    list.add("Item No $i")
                }
                mAdapter.notifyDataSetChanged()
                isLoading = false
            }, 2500)
        }

        fun load() {
            animationView.visibility = View.VISIBLE
            for (i in 0..9) {
                list.add("Item No: $i")
            }
        }


    private fun addScrollerListener()
    {
        //attaches scrollListener with RecyclerView
        rv_loadMore.addOnScrollListener(object : RecyclerView.OnScrollListener()
        {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int)
            {
                super.onScrolled(recyclerView, dx, dy)
                if (!isLoading)
                {
                    //findLastCompletelyVisibleItemPostition() returns position of last fully visible view.
                    ////It checks, fully visible view is the last one.
                    if (layoutManager1.findLastCompletelyVisibleItemPosition() == list.size - 1)
                    {
                        if(list.size!=40){
                            loadMore()
                            isLoading = true
                        }else{

                            Toast.makeText(this@CSHRecyclarLoadMore,"End of list",Toast.LENGTH_SHORT).show()
                        }

                    }
                }
            }
        })
    }
    private fun setUpUI() {
        // supportActionBar?.title = getString(R.string.title_Donor)
        // supportActionBar?.setDisplayHomeAsUpEnabled(true)
        layoutManager1 = LinearLayoutManager(this@CSHRecyclarLoadMore, RecyclerView.VERTICAL, false)

        mAdapter = CSHLoadMoreAdapter(this)
        rv_loadMore.apply {
            adapter = mAdapter
            layoutManager = layoutManager1


        }

    }

}*/