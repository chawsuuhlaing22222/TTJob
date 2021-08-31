package com.me.job.tt.ui.activities

import android.content.Intent
import android.os.Bundle
import com.me.job.R
import kotlinx.android.synthetic.main.activity_basic_information.*
import kotlinx.android.synthetic.main.activity_setting.*
import kotlinx.android.synthetic.main.activity_setting.iv_toolbar_back_arrow

class SettingActivity : BaseActivity() {



    //this is setting activity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        //support toolbar
        setSupportActionBar(toolbar_setting)
        supportActionBar!!.setDisplayShowTitleEnabled(false)

        //back pressed
        iv_toolbar_back_arrow.setOnClickListener {
            onBackPressed()
        }

        //click event for ll_edit_profile
        ll_edit_profile.setOnClickListener {

              //startActivity(Intent(this,EditProfileActivity::class.java))
            startActivity(Intent(this,EditProfileSettingConstraintActivity::class.java))
        }

        iv_edit_profile.setOnClickListener {
           //startActivity(Intent(this,EditProfileActivity::class.java))
            startActivity(Intent(this,EditProfileSettingConstraintActivity::class.java))
        }

        tv_edit_profile.setOnClickListener {
           //startActivity(Intent(this,EditProfileActivity::class.java))
            startActivity(Intent(this,EditProfileSettingConstraintActivity::class.java))
        }
        //end of click event for ll_edit_profile

    }
}