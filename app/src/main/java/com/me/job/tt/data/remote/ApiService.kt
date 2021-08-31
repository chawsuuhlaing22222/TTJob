package com.me.job.tt.data.remote


import com.me.job.tt.data.remote.request.*
import com.me.job.tt.data.remote.response.FoundProfCoverPhoUpdateResponse
import com.me.job.tt.data.remote.response.FoundationBasicInfoUpdateResponse
import com.me.job.tt.data.remote.response.FoundationDataResponse
import com.me.job.tt.data.remote.response.FoundationProfileResponse
import com.me.job.tt.utils.AppConstants
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.GET

interface ApiService {

    /*@POST(AppConstants.COMMENT_URL)
    fun comment(
        @Path("postId") postId: String,
        @Path("userId") userId: String,
        @Body request: CommentRequest
    ): Observable<CommentResponse>*/

    @POST(AppConstants.FOUNDATION_INFO_REQUEST)
    fun getFoundationInfo(@Body request: FoundationInfoRequest)
    : Observable<FoundationDataResponse>


    @POST(AppConstants.FOUNDATION_PROFILE_BASIC_INFO_UPDATE)
    fun updateFoProfileBasicInfo(@Body request: FoundationProfileBasicInfoUpdateRequest)
            : Observable<FoundationBasicInfoUpdateResponse>


    @POST(AppConstants.FOUNDATION_PROFILE_WEBSITE_UPDATE)
    fun updateFoProfileWebsite(@Body request: FoundWebsiteUpdateRequest)
            : Observable<FoundationBasicInfoUpdateResponse>

    @POST(AppConstants.FOUNDATION_PROFILE_NAME_UPDATE)
    fun updateFoProfileName(@Body request: FoundNameUpdateRequest)
            : Observable<FoundationBasicInfoUpdateResponse>

    @POST(AppConstants.FOUNDATION_PROFILE_COVER_PHOTO_UPDATE)
    fun updateFoProfileCoverPhoto(@Body request: FoundCoverPhotoUpdateRequest)
            : Observable<FoundProfCoverPhoUpdateResponse>

}