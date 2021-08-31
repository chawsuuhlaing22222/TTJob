package com.me.job.tt.ui.activities

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.core.view.children
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.me.job.R
import com.me.job.tt.ui.adapters.FoundCoverPhotoAdapter
import kotlinx.android.synthetic.main.activity_recyclar_viewactivity.*
import kotlinx.android.synthetic.main.image_entry.view.*

class RecyclarViewactivity : BaseActivity() {

    private lateinit var mAdapter: FoundCoverPhotoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recyclar_viewactivity)

        var imageList=ArrayList<Int>()
        imageList.add(R.drawable.iu)
        imageList.add(R.drawable.image)
        imageList.add(R.drawable.bank)
        mAdapter=FoundCoverPhotoAdapter(imageList)
        rvFoudCoverPhotos.adapter=mAdapter
       // var mLayoutManager = GridLayoutManager(this, 2)
        var mLayoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        rvFoudCoverPhotos.layoutManager = mLayoutManager


        mAdapter.setOnClickListener(object : FoundCoverPhotoAdapter.OnClickListener{
            override fun onClick() {
                startActivity(Intent(this@RecyclarViewactivity, SettingActivity::class.java))
            }
        })

    }
}