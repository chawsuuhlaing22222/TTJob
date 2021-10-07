package com.me.job.tt.data.remote


import com.me.job.tt.data.remote.request.*
import com.me.job.tt.data.remote.response.*
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
            : Observable<CoverPhotoListResponse>


    // Foundation Donor list
    @GET(AppConstants.FOUNDATION_DONOR_LIST)
    fun foundationDonorList(
        @Path("foundationId") foundationId: String,
        @Path("pageNo") pageNo: Int,
        @Path("countNo") pageCount: Int
    ): Observable<FoundationDonors>

    //  currency exchange list
    @GET(AppConstants.EXCHANGE_LIST)
    fun getCurrencyExchangeList(
    ): Observable<CurrencyExchangeListResponse>

   //item stock list
    @GET(AppConstants.STOCK_LIST)
    fun getStockList(
       @Path("page") pageNo: Int,
       @Path("count") countNo: Int
    ): Observable<StockListResponse>

    //  pick cover photo list
    @GET(AppConstants.PICK_COVER_PHOTO)
    fun getCoverPhotoList(
        @Path("foundationid") foundationid: String,
        @Path("page") pageNo: Int,
        @Path("count") countNo: Int
    ): Observable<CoverPhotoListResponse>


    //theme update
    @POST(AppConstants.UPDATE_THEME_COVER_PHOTO)
    fun updateThemeCoverPhoto(@Body request: ThemeCoverPhotoUpdateRequest)
            : Observable<FoundationDataResponse>




}