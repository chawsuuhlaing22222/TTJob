package com.me.job.tt.viewModels

import androidx.lifecycle.MutableLiveData
import com.me.job.tt.data.remote.request.*
import com.me.job.tt.data.remote.response.FoundProfCoverPhoUpdateResponse
import com.me.job.tt.data.remote.response.FoundationBasicInfoUpdateResponse
import com.me.job.tt.data.remote.response.FoundationDataResponse
import com.me.job.tt.data.remote.response.FoundationProfileResponse
import com.me.job.tt.models.JobModel

class FoundationInfoViewModel:BaseViewModel() {

    val foundationResponse: MutableLiveData<FoundationDataResponse>
    val foundationBasicInfoUpdateResponse: MutableLiveData<FoundationBasicInfoUpdateResponse>
    val foundCoverPhotoUpdateResponse: MutableLiveData<FoundProfCoverPhoUpdateResponse>

    init {
        super.initViewModel()
        foundationResponse=MutableLiveData()
        foundationBasicInfoUpdateResponse=MutableLiveData()
        foundCoverPhotoUpdateResponse=MutableLiveData()
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
                foundCoverPhotoUpdateResponse,
                mErrorLD
            )
        )
    }


}