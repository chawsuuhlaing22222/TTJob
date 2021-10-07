package com.me.job

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.me.job.tt.ui.activities.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_donate.setOnClickListener {

          startActivity(Intent(this,SettingActivity::class.java))
        }
        btn_basic_info.setOnClickListener{
            startActivity(Intent(this,BasicInformationActivity::class.java))
        }

        btn_name_edit.setOnClickListener{
            startActivity(Intent(this,ProfileNameEditActivity::class.java))
        }
        btn_website_edit.setOnClickListener{
            startActivity(Intent(this,WebsiteEditActivity::class.java))
        }

        btn_recycle_vew.setOnClickListener{
            startActivity(Intent(this,RecyclarViewactivity::class.java))
        }

        btn_load_more.setOnClickListener{
            startActivity(Intent(this,CSHRecyclarLoadMore::class.java))
        }

        btn_currency.setOnClickListener{
            startActivity(Intent(this,CurrencyExchangeActivity::class.java))
        }

        btn_motion.setOnClickListener{
            startActivity(Intent(this,MotionLayoutActivity::class.java))
        }

        btn_motiontest.setOnClickListener {
            startActivity(Intent(this,ImageClickMotionActivity::class.java))
        }

        btn_motiontest1.setOnClickListener {
            startActivity(Intent(this,MotionStartActivity::class.java))
        }

        for(i in 0 until 4){
            Log.i("i",i.toString())
        }
    }
}