package com.me.job.tt.utils

import android.content.Context
import android.content.SharedPreferences
import android.media.AudioManager
import android.os.Build
import android.provider.MediaStore
import androidx.media.MediaSessionManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.me.job.tt.data.remote.request.FoundationProfileBasicInfo

class ConfigUtils(context: Context) {

    private var mSharedPreferences: SharedPreferences =
        context.getSharedPreferences(SHARED_PRE, Context.MODE_PRIVATE)




    companion object {
        val FOUNDATION_PROFILE_BASIC_INFO = "foundation_profile_basic_info"
        val SHARED_PRE = "ConfigUtils"

        private var INSTANCE: ConfigUtils? = null

        fun getInstance(): ConfigUtils {
            if (INSTANCE == null) {
                throw RuntimeException("ConfigUtils is being invoked before initializing.")
            }
            val i = INSTANCE
            return i!!
        }

        fun initConfigUtils(context: Context) {
            INSTANCE = ConfigUtils(context)
        }
    }



    fun setFoundationProfileBasficInfo(basicInfo: ArrayList<String>) {

        val basicInfoList = Gson().toJson(basicInfo)
        mSharedPreferences.edit().putString(FOUNDATION_PROFILE_BASIC_INFO, basicInfoList).apply()
    }

    fun getFoundationProfileBasicInfoList(): ArrayList<String>? {
        var arrayList: ArrayList<String>
        val stringList: String? = mSharedPreferences.getString(
            FOUNDATION_PROFILE_BASIC_INFO, ""
        )!!

        val collectionType = object : TypeToken<ArrayList<String>>() {}.type

        return if (!stringList.isNullOrEmpty()) {
            arrayList =
                Gson().fromJson(stringList, collectionType)!!
            arrayList
        } else null


    }


}