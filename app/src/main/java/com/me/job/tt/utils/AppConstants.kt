package com.me.job.tt.utils

import android.net.Uri
import com.me.job.BuildConfig
import com.me.job.tt.utils.AppConstants.Companion.BASE_URL
import java.io.File

class AppConstants {

    enum class COMMENTTYPE {
        HEADER,
        ITEM
    }

    companion object {
        // urls
        // const val BASE_URL_AUTH = "http://auth.amhantayar.com/api/v1/"
        // const val BASE_URL_SERVICE = "http://api.amhantayar.com/api/v1/"
        const val BASE_URL = "https://iix1ylnorg.execute-api.ap-southeast-1.amazonaws.com/test/"    //i have changed import com.me.job.BuildConfig
        const val BASE_URL_SERVICE =BASE_URL  // Fields from product flavor: uat
        const val FOUNDATION_INFO_REQUEST="foundation/profile"
        const val FOUNDATION_PROFILE_BASIC_INFO_UPDATE="foundation/profile/update/basicinfo/request/admin/approve"
        const val FOUNDATION_PROFILE_WEBSITE_UPDATE="foundation/profile/update/website/request/admin/approve"
        const val FOUNDATION_PROFILE_NAME_UPDATE=""
        const val FOUNDATION_PROFILE_COVER_PHOTO_UPDATE="foundation/profile/update/cover/photos"
        const val MY_KEY="AIzaSyBw2ULcqop1_ADRQzui5goiZQSR3y8HG-4"
        const val EXCHANGE_LIST="purchase/exchangerate/list"
        const val STOCK_LIST="purchase/item/stock/list/country/B112908A-CCD8-4712-9C36-7D981607030F/page/{page}/count/{count}"
       // const val PICK_COVER_PHOTO="foundation/cover/photolist/foundationid/223be056-c886-4bed-8205-c587c51d151e/page/{page}/count/{count}"

        const val PICK_COVER_PHOTO="foundation/cover/photolist/foundationid/{foundationid}/page/{page}/count/{count}"
       const val UPDATE_THEME_COVER_PHOTO="foundation/profile/save/theme/coverphotos/slide"


        const val LOGIN_URL = "login"
        const val REGISTER_URL = "register"
        const val REGISTER_USER_URL = "userprofile/register"
        const val FOUNDATION_REGISTER_USER_URL = "foundation/register"
        const val FOUNDATION_PROFILE_UPDATE = "foundation/profile/update/request/admin/approve"
        const val PROFILE_URL = "userprofile/userprofile"
        const val USER_PROFILE_PHOTO_UPDATE = "userprofile/photo/update"
        const val USER_COVER_PHOTO_UPDATE = "userprofile/coverphoto/update"

        const val EDIT_PROFILE_URL = "userprofile/userprofileInformation"
        const val GUEST_URL = "guest"
        const val FEED_URL = "feed"
        const val FEED_URL_NEW = "feed/category/{categoryid}/user/{userid}/page/{pageno}/count/10"

        //test/feed/update/category/all/user/3692868DC48F4147954C88B8E6194B6F/page/1/count/5/search
        const val UPDATE_FEED_URL_ =
            "feed/update/category/{categoryid}/user/{userid}/page/{pageno}/count/10/search"
        const val FEED_URL_DONATION_POST =
            "donationpost/list/userid/{userid}/status/active/page/{pageno}/count/10/search?post=null"
//            "https://iix1ylnorg.execute-api.ap-southeast-1.amazonaws.com/test/donationpost/list/userid/{userid}/status/active/page/{pageno}/count/10/search?post=null"

        //            "donationpost/list/userid/{userid}/status/active/page/{pageno}/count/10/search?post=null"
        const val DONOR_LIST =
            "donationpost/donate/list/foundationid/47c8d564-d03b-4446-a466-2401e140874f/donationtype/2/page/1/count/10/search?username_amount=500"
        const val DONATION_POST_DETAIL =
            "donationpost/detail/postid/{postid}/userid/{userid}"
//            "https://iix1ylnorg.execute-api.ap-southeast-1.amazonaws.com/test/donationpost/detail/postid/{postid}/userid/{userid}"

        //            "donationpost/donate/list/foundationid/00e816ca-b02c-426c-8863-7be48dbba766/donationtype/2/page/1/count/10/search?username_amount=500"
//        const val FOLLOWING_URL = "feed/following/category/{categoryid}/user/{userid}/page/{pageno}/count/10/search"
        //  https://iix1ylnorg.execute-api.ap-southeast-1.amazonaws.com/test/donationpost/donate/list/foundationid/00e816ca-b02c-426c-8863-7be48dbba766/donationtype/2/page/1/count/10/search?username_amount=500
        const val BANK_API = "foundation/bank/list"

        //        const val DONATION_POST_DETAIL = "donationpost/detail/postid/{postid}/userid/{userid}"
        const val DONATION_POST_DONOR_LIST =
            "donationpost/donor/list/id/{id}/donationtype/1/page/1/count/10/search?username_amount=null"
        const val DONATE_PROCESS = "donationpost/donate"
        const val DONATE_ACCESS = "donationpost/donate/accept/reject"

        const val PROFILE_POST_URL = "feed/GetMyPostList"
        const val LIKE_URL = "feed/{postId}/user/{userId}/like"
        const val LOVE_URL = "feed/{postId}/user/{userId}/love"
        const val SAVE_URL = "feed/{postId}/user/{userId}/save"
        const val RATE_URL = "feed/{postId}/user/{userId}/rate"
        const val BLOCK_USER_URL = "userprofile/block"
        const val UNBLOCK_USER_PORFILE ="userprofile/unblock"
        const val VIEW_URL = "feed/{postId}/user/{userId}/viewvideo"
        const val COMMENT_URL = "feed/{postId}/user/{userId}/comment"
        const val GET_COMMENTS_URL = "feed/{postId}/user/{userId}/comments"
        const val GET_BLOCK_LIST = "userprofile/{userId}/block/list/page/{page}/count/{countNo}/search"
        const val GET_PROFILE_SAVED_POSTS_URL = "user/{userid}/post/saved/{page}/{count}"
        const val GET_SAVE_FOLDER = "user/{userid}/saved/folders"
        //        https://iix1ylnorg.execute-api.ap-southeast-1.amazonaws.com/test/user/106661456448217460307/saved/folders
        // https://iix1ylnorg.execute-api.ap-southeast-1.amazonaws.com/test/user/106661456448217460307/folderid/{folderid}/post/saved/page/{pageno}/count/{countno}
        const val GET_FOLDER_WITH_FOLDER_ID = "user/{userid}/folderid/{folderid}/post/saved/page/{pageno}/count/{countno}"
        const val GET_SAVE_PHOTO = "user/{userid}/photos/saved/page/{pageno}/count/{countno}"
        const val GET_SAVE_VIDEO = "user/{userid}/videos/saved/page/{pageno}/count/{countno}"
        const val GET_PROFILE_VIDEOS_URL = "user/{userid}/post/videos/{page}/{count}"

        //   https://iix1ylnorg.execute-api.ap-southeast-1.amazonaws.com/test/user/001/post/videos/1/10
        const val GET_PROFILE_ALL_VIDEO_URL = "user/{userid}/post/videos/{page}/{count}"
        const val GET_PROFILE_PHOTOS_URL = "user/{userid}/post/photos/{page}/{count}"
        const val GET_CERTIFICATE_PHOTOS_URL =
            "foundation/certificate/detail/foundationid/{foundationid}"
        const val GET_ROFILE_DETAILS_URL = "feed/{feedId}/user/{userId}"
        const val ANSWER_URL =
            "competition/single/challenge/{challengeid}/user/{userid}/posttype/{posttype}/Answers"
        const val BANK_DATA_WITH_FOUNDATION_ID = "foundation/{foundationId}/banks"
        const val LOGOUT_URL = "usersetting/logout/{userid}"
        const val SETTING_URL = "usersetting/settings/{userid}"
        const val FOLLOWERS_URL = "friend/user/{userid}/followers/page/{pageno}/count/15"
        const val FOLLOWINGS_URL = "friend/user/{userid}/followings/page/{pageno}/count/15"
        const val FOLLOW_URL = "friend/user/{userid}/follow/{followerid}"
        const val UNFOLLOW_URL = "friend/user/{userid}/unfollow/{followerid}"
        const val SEARCH_URL = "feed/category/all/user/{userid}/page/{pageno}/count/10/search"
        const val NOTIFICATIONS_URL = "notification/{userid}/page/1/count/10/notifications"
        const val UPDATE_NOTIFICATIONS_STATUS_URL = "notification/updatestatus"
        const val FIREND_LIST_URL = "friend/user/{userid}/friends/page/{page}/count/{count}"
        const val PROFILE_WITH_FRIEND = "userprofile/frienduserprofile"
        const val FEEDBACK_URL = "feedback"
        const val UPDATE_TOKEN = "userprofile/refreshToken"
        const val DELETE_COMMENT = "feed/commentdelete"
        const val DELETE_POST = "post/postdelete"
        const val APP_DETAILS = "apps/{id}"

        //        const val FOUNDATION_PROFILE = "foundation/profile/{id}/userid/117147925909768583888"
        const val STATEMENT_OF_ACCOUNTANT = "foundation/bankstatement/create"
        const val CREATE_PARTNER =
            "foundation/partner/create/foundationid/{foundationid}/partnerfoundationid/{partnerfoundationid}"
        const val FOUNDATION_PROFILE = "foundation/profile/{id}/userid/{userId}"

        // https://iix1ylnorg.execute-api.ap-southeast-1.amazonaws.com/test/user/106661456448217460307/post/photos/1/10
        const val PROFILE_PHOTO = "user/{userid}/post/photos/{page}/{count}"

        // const val FOUNDATION_PROFILE = "foundation/profile/fe4aaa57-3048-4b88-a4d5-3847f66b072a"
        const val APP = "apps"
//        const val COMPETITION_QUESTIONS_URL =
//            "competition/challenge/{challengeid}/user/{userid}/question"
//        const val QUESTIONS_URL = "competition/single/user/{userid}/question"
//        const val ANSWER_COMPETITION_QUESTIONS_URL =
//            "competition/challenge/{challengeid}/user/{userid}/answer"

        //        const val CHECK_RESULT_URL = "competition/challenge/{challengeid}/user/{userid}/checkresult"
        const val CHECK_UPDATE_URL = "configuration"

        //        const val CHECK_RESULT_URL =
//            "competition/multiple/challenge/{challengeid}/user/{userid}/checkresult"
        const val COMPETITION_QUESTIONS_URL =
//            "competition/multiple/challenge/{challengeid}/user/{userid}/question"
            "competition/multiple/challenge/{challengeid}/user/{userid}/posttype/{posttype}/question"


        const val QUESTIONS_URL = "competition/single/user/{userid}/posttype/{posttype}/question"
        const val ANSWER_COMPETITION_QUESTIONS_URL =
            "competition/multiple/challenge/{challengeid}/user/{userid}/posttype/{posttype}/answers"

        //            "competition/multiple/challenge/{challengeid}/user/{userid}/answers"
        const val CHECK_RESULT_URL =
            "competition/multiple/challenge/{challengeid}/user/{userid}/checkresult"
        const val CATEGORY_URL = "category/user/{userid}/maincategory"
        const val DONATION_AREA_CATEGORY = "donationarea/categories/page/1/count/10"

        //        const val FOUNDATION_DELETE =
//            "https://iix1ylnorg.execute-api.ap-southeast-1.amazonaws.com/test/foundation/delete/{foundationId}/request/admin/approve"
//        const val FOUNDATION_DELETE =
//            "https://iix1ylnorg.execute-api.ap-southeast-1.amazonaws.com/test/foundation/delete/{foundationId}/request/admin/approve"
        const val DONATION_INFO_CATEGORY_CATEGORY_URL = "donationarea/categories/page/1/count/10"
        const val SUB_CATEGORY_URL = "category/user/{userid}/subcategory/{categoryid}"
        const val POST_CATEGORY_URL = "category"
        const val POST_SUB_CATEGORY_URL = "category"
        const val REWARD_URL = "reward/user/{userid}"
        const val CLAIM_REWARD_URL = "reward/claim"
        const val CLAIM_TASK_URL = "reward/claim"
        const val CHECK_COIN_AND_DATE_TIME = "competition/check/coin/datetime/user/{userid}"

        const val REACITON_ALL_URL = "feed/like/love/user/{userid}/post/{postid}"
        const val REQUEST_POST_VIDEO_URL = "post/video/{userId}"
        const val REQUEST_POST_VIDEO_UPDATE_URL = "post/video/post/{postid}/user/{userid}"
        const val REQUEST_POST_PHOTO_URL = "post/photo/{userId}"
        const val REQUEST_CERTIFICATE_PHOTO_UPLOAD = "foundation/certificate/create"
        const val REQUEST_POST_PHOTO_UPDATE_URL = "post/photo/post/{postid}/user/{userid}"

        const val POST_VIDEO_URL = "post/video/{userId}"
        const val POST_PHOTO_URL = "post/photo/{userId}"
        const val POST_PHOTO_UPDATE_URL = "post/photo/update"
        const val POST_VIDEO_UPDATE_URL = "post/video/update"

        const val IMAGE = "image"
        const val IMAGE_VAL = 1
        const val VIDEO = "video"
        const val YOUTUBE_VIEO = "youtubevideo"
        const val QUESTION = "question"

        //websocket constants
        const val FOUND = "found"
        const val ACCEPT = "accept"
        const val DECLINE = "decline"

        //tab numbers
        const val TAB_FEED = "feed"
        const val TAB_COMPETITION = "competition"
        const val TAB_ADD = "add"
        const val TAB_MESSAGE = "message"
        const val TAB_NOTI = "noti"


        // access types
        const val AC_GMAIL = "gmail"
        const val AC_FACEBOOK = "facebook"

        // temp variables  //csh
        /*var tmpPost: Post? = null
        var tmpPostDonationList: DonationPost? = null
        var tmpImageList: List<Uri>? = null
        var tmpCompetition: GetCompetitonResponse? = null
        var tmpQuestionResponse: GetQuestionResponse? = null
        var tmpCredentials: Credentials? = null
        var tmpFile: File? = null
        var tmpFileList: List<File>? = null
        var tmpPhoneNo: String? = null*/

        //Send Bird
        const val SEND_BIRD_APP_ID = "EC9253CA-5A8E-42AD-8FEF-5B8B584AFC53"

        //Firebase Refresh Token
        const val REFRESH_TOKEN = "refresh-token"

        const val TABLE_RECENT_SEARCH = "recent_search"


        // Post

        const val POPULAR_POST_URL =
            "feed/popular/category/{categoryId}/user/{userId}/page/{page}/count/{count}/search"
        const val FOLLOWING_POST_URL =
            "feed/following/category/{categoryId}/user/{userId}/page/{page}/count/{count}/search"
        const val UPLOAD_DONATION_POST_URL = "donationpost/create"


        // Information

        // Method
        // Type = GET
        const val INFORMATION_URL = "donationinformation/informationarea/page/1/count/10"

        //  donationarea/category/detail/posts/{categoryid}/userid/{userid}/page/{pageno}/count/{countno}
        const val DONATION_CATEGORY_DETAIL =
            "donationarea/category/detail/posts/{categoryid}/userid/{userid}/page/{pageno}/count/{countno}"
        const val FOUNDATION_PARTNER_CREATE_LIST =
            "foundation/other/list/foundationid/{foundationid}/page/{pageno}/count/{countno}/search?foundationname=null"
        const val FOUNDATION_PARTNER_SEARCH =
            "foundation/other/list/foundationid/{foundationid}/page/{pageno}/count/{countno}/search?"
        const val DONATION_FOUNDATION_LIST =
            "donationarea/foundations/list/page/{pageno}/count/{countno}/search?foundationname=null"

        //foundation/other/list/foundationid/{foundationid}/page/{pageno}/count/{countno}/search?foundationname=null
        const val DONATION_FOUNDATION_LIST_SEARCH =
            "donationarea/foundations/list/page/{pageno}/count/{countno}/search?"

        const val PARTNER_REQUEST_LIST =
            "foundation/partner/request/list/foundationid/{foundationid}/page/{pageno}/count/{countno}/search?partner=null"
        const val PARTNER_SEND_REQUEST =
            "foundation/partner/request/foundationid/{foundationid}/partnerfoundationid/{partnerfoundationid}"
        const val PARTNER_COMFIRM = "foundation/partner/confirmation"
        const val PARTNER_REJECT = "foundation/partner/remove"

        // Method
        // Type = GET
        const val INFORMATION_DETAIL_URL =
            "donationinformation/informationarea/detail/category/{categoryId}/year/{year}/month/{month}/page/{page}/count/{count}"

        const val DONATION_REGION_DETAIL_URL =
            "donationinformation/informationarea/detail/region/{regionid}/year/{year}/month/{month}/page/{pageno}/count/{countno}"

        // Donation Post

        //Action Type
        //like
        const val DONATION_LIKE = "like"

        // love
        const val DONATION_LOVE = "love"

        // Method
        // like
        const val DONATION_POST_LIKE =
            "https://iix1ylnorg.execute-api.ap-southeast-1.amazonaws.com/test/donationpost/{postid}/user/{userid}/like"

        // Method
        // love
        const val DONATION_POST_LOVE =
            "https://iix1ylnorg.execute-api.ap-southeast-1.amazonaws.com/test/donationpost/{postid}/user/{userid}/love"

        //Method
        // Delete
        const val DONATION_POST_DELETE =
            "donationpost/delete/postid/{postid}/status/{status}"
//            "https://iix1ylnorg.execute-api.ap-southeast-1.amazonaws.com/test/donationpost/delete/postid/{postid}/status/{status}"

        const val STATEMENT_DELETE = "foundation/bankstatement/delete"
        // Foundation Following

        // Method
        //Type = GET
        // Foundation Follower

        const val FOUNDATION_FOLLOWER =
            "foundation/followers/{foundationId}/page/{page}/count/{pageCount}?search=null"

        // Method
        // TYPE = POST
        // Foundation follow
        const val FOUNDATION_FOLLOW =
            "foundation/follow/{foundationId}/follower/{userId}"

        // Method
        // TYPE = POST
        // Foundation Unfollow
        const val FOUNDATION_UNFOLLOW =
            "foundation/unfollow/{foundationId}/follower/{userId}"


        // Foundation Rating

        // Method
        // Type = GET
        // Foundation Rating
        const val FOUNDATION_RATING_LIST =
            "foundation/rating/userlist/foundation/{foundationId}/page/{page}/count/{pageCount}?search=null"

        // Method
        // Type = POST
        // Foundation Rate
        const val FOUNDATION_RATE =
            "foundation/rating/{foundationId}/user/{userId}/rate/{rateCount}"


        // Method
        // Type = POST
        // Foundation Review
        const val FOUNDATION_REVIEW =
            "foundation/review/{review}/{foundationId}/user/{userId}"

        // Method
        // Type = GET
        // Foundation Review User List
        const val FOUNDATION_REVIEW_USER_LIST =
            "foundation/review/userlist/foundation/{foundationId}/page/{pageNo}/count/{countNo}?search=null"

        // Method
        // Type = GET
        // Foundation Review User List
        const val FOUNDATION_USER_REVIEW_TEXT_LIST =
            "https://iix1ylnorg.execute-api.ap-southeast-1.amazonaws.com/test/foundation/review/detail/foundationid/{foundationId}"


        // Method
        // Type = Get
        // Foundation Donor List
        const val FOUNDATION_DONOR_LIST =
            "https://iix1ylnorg.execute-api.ap-southeast-1.amazonaws.com/test/foundation/profile/{foundationId}/donorlist/page/{pageNo}/count/{countNo}/search?username=null"

        // Method
        // Type = GET
        // Foundation Delete
        const val FOUNDATION_DELETE = "foundation/delete/{foundationId}/request/admin/approve"


        // Method
        // Type = GET
        // Donation Area Additional Menu
        const val DONATION_AREA_MENU =
            "https://iix1ylnorg.execute-api.ap-southeast-1.amazonaws.com/test/donationarea/additional/menu/page/{pageNo}/count/{countNo}"

        //            "https://iix1ylnorg.execute-api.ap-southeast-1.amazonaws.com/test/foundation/review/userlist/foundation/{foundationId}/page/{pageNo}/count/{countNo}?search=null"
//https://iix1ylnorg.execute-api.ap-southeast-1.amazonaws.com/test/foundation/partner/request/foundationid/{foundationid}/partnerfoundationid/{partnerfoundationid}
        const val FOUNDATION_PARTNER_LIST =
            "foundation/partner/list/foundationid/{foundationid}/page/{pageno}/count/{countno}/search?partner =null"

        // Post Photo Upload
        const val POST_PHOTO_UPLOAD = "post/photo/{userId}"

        // Post Video Upload
        const val POST_VIDEO_UPLOAD =
            "https://iix1ylnorg.execute-api.ap-southeast-1.amazonaws.com/test/post/video/{userId}"

        // POST PHOTO UPDATE
        const val POST_PHOTO_UPDATE =
            "https://iix1ylnorg.execute-api.ap-southeast-1.amazonaws.com/test/post/photo/update"

        //POST VIDEO UPDATE
        const val POST_VIDEO_UPDATE =
            "https://iix1ylnorg.execute-api.ap-southeast-1.amazonaws.com/test/post/video/update"


        // Comment & Reply Comment

        const val ALL_COMMENT =
//            "feed/{postId}/user/{userId}/parentcommentid/null/page/{page}/count/{count}/comments"
            "feed/{feedId}/user/{userId}/parentcommentid/null/page/{page}/count/{count}/comments"

        const val CHILD_COMMENT =
//            "feed/{postId}/user/{userId}/parentcommentid/null/page/{page}/count/{count}/comments"
            "feed/{feedId}/user/{userId}/parentcommentid/{parentCommentId}/page/{page}/count/{count}/comments"


        // DELETE ALLL COMMENT
        const val All_COMMENT_DELETE =
            "https://iix1ylnorg.execute-api.ap-southeast-1.amazonaws.com/test/feed/commentdelete"
        //"https://iix1ylnorg.execute-api.ap-southeast-1.amazonaws.com/test/feed/{postId}/user/{userId}/parentcommentid/{parentCommentId}/page/{page}/count/{count}/comments"

        // Share Post
        const val SHARE_POST = "post/share"

        // Hide Post
        const val HIDE_POST = "post/hide"

        // Save Post

        //Save Post Default Api
        const val SAVE_POST_DEFAULT_FOLDER = "feed/{postId}/user/{userId}/save"

        // get all save collection
        const val SAVE_POST_LIST = "user/{userId}/saved/folders"

        // update post base 64
        const val UPDATE_POST_BASE_64 = "post/photo/update"
        // update video base 64

        // update comment
        const val UPDATE_COMMENT = "feed/edit/comment/{commentId}/feed/{feedId}"

        const val FOUNDATION_DONATE_LIST =
            "donationpost/donate/list/foundationid/{foundationid}/donationtype/{donationtype}/status/{status}/page/{pageNo}/count/{countNo}/search?username_amount=null"

        const val MAPS_API_KEY = "AIzaSyBw2ULcqop1_ADRQzui5goiZQSR3y8HG-4"

        const val JOB_SENDBIRD_API="5FEA5540-5669-4E6F-9EDE-CB6013C87E3D"
    }





}