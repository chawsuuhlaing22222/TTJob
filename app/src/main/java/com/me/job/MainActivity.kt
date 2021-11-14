package com.me.job

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.me.job.tt.ui.activities.*
import com.me.job.tt.utils.ConfigUtils
import com.sendbird.android.SendBird
import kotlinx.android.synthetic.main.activity_main.*
import java.text.DateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       // login("jobuser1")

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

        btn_collapsed.setOnClickListener {
            startActivity(Intent(this,CollapsingToolbarTest::class.java))
        }
        btn_collapsed2.setOnClickListener {
            startActivity(Intent(this,CollapsedToolbarTesting2::class.java))
        }

        for(i in 0 until 4){
            Log.i("i",i.toString())
        }

        val cal = Calendar.getInstance()
        cal.set(Calendar.YEAR, 2021)
        cal.set(Calendar.MONTH, 3)   //for show  month in string no need to plus one (auto correct for string month)
        cal.set(Calendar.DAY_OF_MONTH, 3)
        val currentDate = DateFormat.getDateInstance(DateFormat.MEDIUM).format(cal.time)
        tv_date.text = currentDate


        val formatter = DateTimeFormatter.ofPattern("M/d/yyyy")
        val date: LocalDate? = LocalDate.parse("3/3/2021",formatter)
        var formattedDate = date?.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));

        tv_date2.text=formattedDate.toString()

        btn_cover2.setOnClickListener {
            startActivity(Intent(this,CoverGreyActivity::class.java))
        }

        btn_chat.setOnClickListener {
            startActivity(Intent(this,ChatUIKITActivity::class.java))
        }


    }

    fun login(userId: String?) {
        SendBird.connect(userId,
            SendBird.ConnectHandler { user, e ->
                if (e != null) { // Error.
                    Toast.makeText(this, "${e.localizedMessage}${e.message}", Toast.LENGTH_LONG)
                        .show()
                    //ConfigUtils.getInstance().saveConnection(false)
                    return@ConnectHandler
                } else {
                    //ConfigUtils.getInstance().saveConnection(true)

                    //   Toast.makeText(this, "Connected", Toast.LENGTH_LONG).show()
                }
            })
    }
}