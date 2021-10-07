package com.me.job.tt.models

import androidx.lifecycle.MutableLiveData
import com.me.job.tt.data.remote.request.*
import com.me.job.tt.data.remote.response.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.http.Path

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
        responseLD: MutableLiveData<CoverPhotoListResponse>,
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

    //Foundation Donor List
    fun foundationDonorList(
        foundationId: String,
        page: Int,
        pageCount: Int,
        responseLD: MutableLiveData<FoundationDonors>,
        errorLd: MutableLiveData<String>
    ): Disposable {
        return mApiService.foundationDonorList(foundationId, page, pageCount)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responseLD.value = it
            },
                {
                    errorLd.value = it.localizedMessage
                }
            )
    }

    //currency exchange List
    fun getCurrencyExchangeList(
        responseLD: MutableLiveData<CurrencyExchangeListResponse>,
        errorLd: MutableLiveData<String>
    ): Disposable {
        return mApiService.getCurrencyExchangeList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responseLD.value = it
            },
                {
                    errorLd.value = it.localizedMessage
                }
            )
    }

    //currency exchange List
    fun getStockList(
        page: Int,
        countNo: Int,
        responseLD: MutableLiveData<StockListResponse>,
        errorLd: MutableLiveData<String>
    ): Disposable {
        return mApiService.getStockList(page,countNo)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responseLD.value = it
            },
                {
                    errorLd.value = it.localizedMessage
                }
            )
    }


    //get cover photo List
    fun getCoverPhotoList(
        foundationid: String,
        pageNo: Int,
        countNo: Int,
        responseLD: MutableLiveData<CoverPhotoListResponse>,
        errorLd: MutableLiveData<String>
    ): Disposable {
        return mApiService.getCoverPhotoList(foundationid,pageNo,countNo)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responseLD.value = it
            },
                {
                    errorLd.value = it.localizedMessage
                }
            )
    }


    //update theme cover photo
    fun updateThemeCoverPhoto(
        request: ThemeCoverPhotoUpdateRequest,
        responseLD: MutableLiveData<FoundationDataResponse>,
        errorLd: MutableLiveData<String>
    ): Disposable {
        // val authToken = ConfigUtils.getInstance().loadAuthToken()
        return mApiService.updateThemeCoverPhoto(
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