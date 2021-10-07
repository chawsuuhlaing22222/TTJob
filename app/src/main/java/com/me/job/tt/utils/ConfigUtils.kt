package com.me.job.tt.utils

import android.content.Context
import android.content.SharedPreferences
import android.media.AudioManager
import android.os.Build
import android.provider.MediaStore
import androidx.media.MediaSessionManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ConfigUtils(context: Context) {

    private var mSharedPreferences: SharedPreferences =
        context.getSharedPreferences(SHARED_PRE, Context.MODE_PRIVATE)

    private var audioManager =
        context.getSystemService(Context.AUDIO_SERVICE) as AudioManager


    companion object {
        val KEY_FIRST_TIME = "first-time"
        val KEY_ACCESS_ID = "access-id"
        val KEY_PROFILE_ACCESS_ID = "profile-access-id"
        val FOUNDATION_ID = "foundation-id"
        val KEY_USER_ID = "user-id"
        val KEY_PROFILE_USER_ID = "profile-user-id"
        val KEY_ACC_TYPE = "account-type"
        val KEY_AUTH_TOKEN = "auth-token"
        val KEY_PAGE_NO = "page-no"
        val SHARED_PRE = "ConfigUtils"
        val KEY_CURRENT_USER_PROFILE_URL = "Profile-Url"
        val KEY_CURRENT_USER_NAME = "current-user-name"
        val KEY_TIME_OUT = "Time-Out"
        val KEY_TIME_LEFT = "Time-Left"
        val KEY_VERSION_CODE = "version-code"
        val KEY_NOTI_COUNT = "noti-count"
        val KEY_FOLLOWER = "follower"
        val SELECTED_CHECKBOX_TERM_AND_COND = "selected-checkbox-term-condition"
        val KEY_USER_LIST = "user-list"
        val KEY_SELECTED_TAB = "tab"
        val KEY_CATEGORY_ID = "category-id"


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

    fun setFirstTime(isFirstTime: Boolean) {
        mSharedPreferences.edit().putBoolean(KEY_FIRST_TIME, isFirstTime).apply()
    }

    fun isFirstTime(): Boolean {
        return mSharedPreferences.getBoolean(KEY_FIRST_TIME, true)
    }

    fun saveAccessId(accessId: String) {
        mSharedPreferences.edit().putString(KEY_ACCESS_ID, accessId).apply()
    }

    fun loadAccessId(): String {
        return mSharedPreferences.getString(KEY_ACCESS_ID, "")!!
    }

    fun saveCategoryId(categoryId: String) {
        mSharedPreferences.edit().putString(KEY_CATEGORY_ID, categoryId).apply()
    }

    fun loadCategoryId(): String {
        return mSharedPreferences.getString(KEY_CATEGORY_ID, "")!!
    }


    fun saveSelectedCheckTermAndCondition(isChecked: Boolean) {
        mSharedPreferences.edit().putBoolean(SELECTED_CHECKBOX_TERM_AND_COND, isChecked).apply()
    }

    fun loadSelectedCheckTermAndCondition(): Boolean {
        return mSharedPreferences.getBoolean(SELECTED_CHECKBOX_TERM_AND_COND, false)!!
    }


    fun saveProfileAccessId(accessId: String) {
        mSharedPreferences.edit().putString(KEY_PROFILE_ACCESS_ID, accessId).apply()
    }

    fun loadProfileAccessId(): String {
        return mSharedPreferences.getString(KEY_PROFILE_ACCESS_ID, "")!!
    }


    fun saveFoundationId(foundationId: String) {
        mSharedPreferences.edit().putString(FOUNDATION_ID, foundationId).apply()

    }

    fun loadFoundationId(): String {
        return mSharedPreferences.getString(FOUNDATION_ID, "")!!
    }

    fun saveUserId(userId: String) {
        mSharedPreferences.edit().putString(KEY_USER_ID, userId).apply()
    }

    fun loadUserId(): String {
        return mSharedPreferences.getString(KEY_USER_ID, "")!!
    }

    fun saveProfileUserId(userId: String) {
        mSharedPreferences.edit().putString(KEY_PROFILE_USER_ID, userId).apply()
    }

    fun loadProfileUserId(): String {
        return mSharedPreferences.getString(KEY_PROFILE_USER_ID, "")!!
    }


    fun saveAccessType(type: String) {
        mSharedPreferences.edit().putString(KEY_ACC_TYPE, type).apply()
    }

    fun loadAccessType(): String {
        return mSharedPreferences.getString(KEY_ACC_TYPE, "")!!
    }

    fun saveAuthToken(token: String) {
        mSharedPreferences.edit().putString(KEY_AUTH_TOKEN, "Bearer $token").apply()
    }

    fun loadAuthToken(): String {
        return mSharedPreferences.getString(KEY_AUTH_TOKEN, "")!!
    }

    fun saveNotiCount(count: Int) {
        mSharedPreferences.edit().putInt(KEY_NOTI_COUNT, count).apply()
    }

    fun loadNotiCount(): Int {
        return mSharedPreferences.getInt(KEY_NOTI_COUNT, 0)!!
    }

    fun savePageNo(pageNo: Int) {
        mSharedPreferences.edit().putInt(KEY_PAGE_NO, pageNo).apply()
    }

    fun loadPageNo(): Int {
        return mSharedPreferences.getInt(KEY_PAGE_NO, 1)
    }

    fun saveCurrentUserProfileUrl(url: String) {
        mSharedPreferences.edit().putString(KEY_CURRENT_USER_PROFILE_URL, url).apply()
    }

    fun loadCurrentUserProfileUrl(): String {
        return mSharedPreferences.getString(KEY_CURRENT_USER_PROFILE_URL, "")!!
    }

    fun volumeUp(): Int {
        audioManager.adjustVolume(AudioManager.ADJUST_RAISE, AudioManager.FLAG_PLAY_SOUND)
        return currentVolume()
    }


    fun volumeDown(): Int {
        audioManager.adjustVolume(AudioManager.ADJUST_LOWER, AudioManager.FLAG_PLAY_SOUND)
        return currentVolume()
    }

    fun currentVolume(): Int {
        val currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)
        val maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)
        return 100 * currentVolume / maxVolume
    }

    fun muteVolume() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            audioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_MUTE, 0)
        } else {
            audioManager.setStreamMute(AudioManager.STREAM_MUSIC, true)
        }

    }

    fun convertMilltoMinute(milliseconds: Long): String {
        val minutes = milliseconds / 1000 / 60
        val seconds = milliseconds / 1000 % 60
        return "$minutes : $seconds"
    }

    fun setCompetitionTimeOut(isTimeOut: Boolean) {
        mSharedPreferences.edit().putBoolean(KEY_TIME_OUT, isTimeOut).apply()
    }

    fun isCompetitionTimeOut(): Boolean {
        return mSharedPreferences.getBoolean(KEY_TIME_OUT, false)
    }

    fun setCompetitionTimeLeft(timeInMillisecond: Long) {
        mSharedPreferences.edit().putLong(KEY_TIME_LEFT, timeInMillisecond).apply()
    }

    fun loadCompetitionTimeLeft(): Long {
        return mSharedPreferences.getLong(KEY_TIME_LEFT, 0)
    }

    fun saveCurrentUserName(userName: String) {
        mSharedPreferences.edit().putString(KEY_CURRENT_USER_NAME, userName).apply()
    }

    fun loadCurrentUserName(): String {
        return mSharedPreferences.getString(KEY_CURRENT_USER_NAME, "")!!
    }

    fun saveVersionCode(versionCode: Int) {
        mSharedPreferences.edit().putInt(KEY_VERSION_CODE, versionCode).apply()
    }

    fun loadVersionCode(): Int {
        return mSharedPreferences.getInt(KEY_VERSION_CODE, 0)
    }

    fun setUserList(userList: ArrayList<String>) {

        val mUserList = Gson().toJson(userList)
        mSharedPreferences.edit().putString(KEY_USER_LIST, mUserList).apply()
    }

    fun getUserList(): ArrayList<String>? {
        var arrayList: ArrayList<String>
        val stringList: String? = mSharedPreferences.getString(
            KEY_USER_LIST, ""
        )!!
        val collectionType = object : TypeToken<ArrayList<String>>() {}.type
        return if (!stringList.isNullOrEmpty()) {
            arrayList =
                Gson().fromJson(stringList, collectionType)!!
            arrayList
        } else null


    }

    fun setUserFollowerId(userFollwerId: String) {
        mSharedPreferences.edit().putString(KEY_FOLLOWER, userFollwerId).apply()
    }

    fun loadUserFollwer(): String {
        return mSharedPreferences.getString(KEY_FOLLOWER, "")!!
    }

    fun setTabSelected(selectedTab: String) {
        mSharedPreferences.edit().putString(KEY_SELECTED_TAB, selectedTab).apply()
    }

    fun loadTabSelected(): String {
        return mSharedPreferences.getString(KEY_SELECTED_TAB, "")!!
    }
}