package com.me.job.tt.viewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.me.job.tt.data.remote.request.*
import com.me.job.tt.data.remote.response.*
import com.me.job.tt.models.JobModel

class FoundationInfoViewModel:BaseViewModel() {

    val foundationResponse: MutableLiveData<FoundationDataResponse>
    val foundationBasicInfoUpdateResponse: MutableLiveData<FoundationBasicInfoUpdateResponse>
    val foundCoverPhotoUpdateResponse: MutableLiveData<FoundProfCoverPhoUpdateResponse>
    val mFoundationDonorList: MutableLiveData<FoundationDonors>
    val mCurrencyExchangeListResponse: MutableLiveData<CurrencyExchangeListResponse>
    val mStockListResponse:MutableLiveData<StockListResponse>
    val mFoundCoverPhotoListResonse:MutableLiveData<CoverPhotoListResponse>
    val mFoundCoverPhotoListResonseNew:MutableLiveData<CoverPhotoListResponse>
    val mThemeCoverPhotoUpdateResponse:MutableLiveData<FoundationDataResponse>
    init {
        super.initViewModel()
        foundationResponse=MutableLiveData()
        foundationBasicInfoUpdateResponse=MutableLiveData()
        foundCoverPhotoUpdateResponse=MutableLiveData()
        mFoundationDonorList=MutableLiveData()
        mCurrencyExchangeListResponse=MutableLiveData()
        mStockListResponse=MutableLiveData()
        mFoundCoverPhotoListResonse=MutableLiveData()
        mThemeCoverPhotoUpdateResponse=MutableLiveData()
        mFoundCoverPhotoListResonseNew= MutableLiveData()
    }

    fun getFoundationInfoResponse(foundationInfoRequest:FoundationInfoRequest){
        mCompositeDisposable.add(
            JobModel.getInstance().getFoundationInfo(
                foundationInfoRequest,
                foundationResponse,
                mErrorLD
            )
        )
    }

    fun updateFoProfileBasicInfo(fouBasicInfoUpdateReq:FoundationProfileBasicInfoUpdateRequest){
        mCompositeDisposable.add(
            JobModel.getInstance().updateFoProfileBasicInfo(
                fouBasicInfoUpdateReq,
                foundationBasicInfoUpdateResponse,
                mErrorLD
            )
        )
    }

    fun updateFoProfileWebsite(fouWebsiteUpdateReq:FoundWebsiteUpdateRequest){
        mCompositeDisposable.add(
            JobModel.getInstance().updateFoProfileWebsiteLink(
                fouWebsiteUpdateReq,
                foundationBasicInfoUpdateResponse,
                mErrorLD
            )
        )
    }

    fun updateFoProfileName(foudNameUpdateRequest:FoundNameUpdateRequest){
        mCompositeDisposable.add(
            JobModel.getInstance().updateFoProfileName(
                foudNameUpdateRequest,
                foundationBasicInfoUpdateResponse,
                mErrorLD
            )
        )
    }

    fun updateFoProfileCoverPhoto(foudCoverPhotoUpdateRequest:FoundCoverPhotoUpdateRequest){
              mCompositeDisposable.add(
            JobModel.getInstance().updateFoProfileCoverPhoto(
                foudCoverPhotoUpdateRequest,
                mFoundCoverPhotoListResonseNew,
                mErrorLD
            )
        )


    }

    // Foundation DonorList
    fun foundationDonorList(foundationId: String, pageNo: Int, pageCount: Int) {
        mCompositeDisposable.add(
            JobModel.getInstance().foundationDonorList(
                foundationId = foundationId,
                page = pageNo,
                pageCount = pageCount,
                responseLD = mFoundationDonorList,
                errorLd = mErrorLD
            )
        )
    }

    // currency exchange list
    fun getCurrencyExchangeList() {
        mCompositeDisposable.add(
            JobModel.getInstance().getCurrencyExchangeList(
                responseLD = mCurrencyExchangeListResponse,
                errorLd = mErrorLD
            )
        )
    }

    //stock list
    fun getStockList(pageNo:Int,countNo:Int){
        mCompositeDisposable.add(
            JobModel.getInstance().getStockList(
                pageNo,
                countNo,
                mStockListResponse,
                mErrorLD
            )
        )
    }

    //coverphoto list
    fun getCoverPhotoList(foundationId: String,pageNo:Int,countNo:Int){
        mCompositeDisposable.add(
            JobModel.getInstance().getCoverPhotoList(
                foundationId,
                pageNo,
                countNo,
                mFoundCoverPhotoListResonse,
                mErrorLD
            )
        )
    }

    //update theme coverphoto
    fun updateThemeCoverPhoto(themeCoverPhotoUpdateRequest: ThemeCoverPhotoUpdateRequest){
        mCompositeDisposable.add(
            JobModel.getInstance().updateThemeCoverPhoto(
                themeCoverPhotoUpdateRequest,
                mThemeCoverPhotoUpdateResponse,
                mErrorLD
            )
        )
    }




}