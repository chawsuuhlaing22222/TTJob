package com.me.job.tt.models

import androidx.lifecycle.MutableLiveData
import com.me.job.tt.data.remote.request.*
import com.me.job.tt.data.remote.response.FoundProfCoverPhoUpdateResponse
import com.me.job.tt.data.remote.response.FoundationBasicInfoUpdateResponse
import com.me.job.tt.data.remote.response.FoundationDataResponse
import com.me.job.tt.data.remote.response.FoundationProfileResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class JobModel :BaseModel() {

    companion object {
        private var INSTANCE: JobModel? = null
        fun getInstance(): JobModel {
            if (INSTANCE == null) {
                INSTANCE = JobModel()
            }
            val i = INSTANCE
            return i!!
        }
    }

    fun getFoundationInfo(
        request:FoundationInfoRequest,
        responseLD: MutableLiveData<FoundationDataResponse>,
        errorLd: MutableLiveData<String>
    ): Disposable {
       // val authToken = ConfigUtils.getInstance().loadAuthToken()
        return mApiService.getFoundationInfo(
            request
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    responseLD.value = it
                }, {
                    errorLd.value = it.localizedMessage
                }
            )

    }

    fun updateFoProfileBasicInfo(
        request:FoundationProfileBasicInfoUpdateRequest,
        responseLD: MutableLiveData<FoundationBasicInfoUpdateResponse>,
        errorLd: MutableLiveData<String>
    ): Disposable {
        // val authToken = ConfigUtils.getInstance().loadAuthToken()
        return mApiService.updateFoProfileBasicInfo(
            request
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    responseLD.value = it
                }, {
                    errorLd.value = it.localizedMessage
                }
            )

    }

    fun updateFoProfileWebsiteLink(
        request:FoundWebsiteUpdateRequest,
        responseLD: MutableLiveData<FoundationBasicInfoUpdateResponse>,
        errorLd: MutableLiveData<String>
    ): Disposable {
        // val authToken = ConfigUtils.getInstance().loadAuthToken()
        return mApiService.updateFoProfileWebsite(
            request
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    responseLD.value = it
                }, {
                    errorLd.value = it.localizedMessage
                }
            )

    }


    fun updateFoProfileName(
        request: FoundNameUpdateRequest,
        responseLD: MutableLiveData<FoundationBasicInfoUpdateResponse>,
        errorLd: MutableLiveData<String>
    ): Disposable {
        // val authToken = ConfigUtils.getInstance().loadAuthToken()
        return mApiService.updateFoProfileName(
            request
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    responseLD.value = it
                }, {
                    errorLd.value = it.localizedMessage
                }
            )

    }

    fun updateFoProfileCoverPhoto(
        request: FoundCoverPhotoUpdateRequest,
        responseLD: MutableLiveData<FoundProfCoverPhoUpdateResponse>,
        errorLd: MutableLiveData<String>
    ): Disposable {
        // val authToken = ConfigUtils.getInstance().loadAuthToken()
        return mApiService.updateFoProfileCoverPhoto(
            request
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    responseLD.value = it
                }, {
                    errorLd.value = it.localizedMessage
                }
            )

    }

}