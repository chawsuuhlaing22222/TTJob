package com.me.job.tt.data.remote.response

import com.me.job.tt.data.vos.Paging
import java.io.Serializable

data class DonatePostList(
    val donationPosts:List<DonationPost>,
    val paging:Paging) : Serializable