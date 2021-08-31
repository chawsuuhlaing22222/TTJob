package com.me.job.tt.ui.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.me.job.R
import com.me.job.tt.utils.ConfigUtils
import kotlinx.android.synthetic.main.activity_shared_ref_test.*

class SharedRefTestActivity : BaseActivity() {

    //to test sharedReference
    //need to delete for this activity
    companion object {
        fun newIntent(
            context: Context
        ): Intent {
            val intent = Intent(context, SharedRefTestActivity::class.java)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_ref_test)

        ConfigUtils.initConfigUtils(this)
        var datalis=ConfigUtils.getInstance().getFoundationProfileBasicInfoList()
        tv_sharedRe_list.text = datalis?.size.toString()
    }
}