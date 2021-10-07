package com.me.job.tt.data.remote.request


import com.me.job.tt.data.remote.response.Photos
import com.me.job.tt.data.remote.response.Videos
import com.me.job.tt.data.vos.BankInfoVos
import com.me.job.tt.data.vos.FoundationPartnerModel
import java.io.Serializable

data class FoundationProfileRequest(
    val id: String,
    val userid: String,
    val foundation_type: String,
    val name: String,
    val location: String,
    val first_nrc_no: String,
    val first_nrc_front_name: String,
    val first_nrc_back_name: String,
    val second_nrc_no: String,
    val second_nrc_front_name: String,
    val second_nrc_back_name: String,
    val founder_email: String,
    val founder_phoneno: String,
    val foundation_email: String,
    val foundation_phoneno: String,
    val bank_acc_no: List<BankInfoVos>,
    val police_recommend: String,
    val township_recommend: String,
    val ward_recommend: String,
    val record: String,
    val foundation_logo: String,
    val theme: String,
    val foundation_photos: List<String>,
    val history: String,
    val follower_count: Int,
    val review_count: Int,
    val certificate_photo: List<String>,
    val created_datetime: String,
    val updated_datetime: String,
    val status: Int,
    val foundation_partner: List<FoundationPartnerModel>,
    val followstatus: String,
    val foundation_bankstatementfiles: ArrayList<String>,
    val donor_count: Int,
    val raiting_star: Float,
    val photos: Photos,
    val videos: Videos,
    val website: String

) : Serializable