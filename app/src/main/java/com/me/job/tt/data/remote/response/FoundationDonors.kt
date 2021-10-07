package com.me.job.tt.data.remote.response

import com.me.job.tt.data.vos.Paging


data class FoundationDonors(
    val success: Boolean,
    val message: String,
    val data: MutableList<Data>,
    val paging: Paging,
    val error: Boolean
) {
    data class Data(
        val id: String,
        val userid: String,
        val username: String,
        val userphoto: String,
        val postid: String,
        val foundationid: String,
        val donationtype: Int,
        val bank_accountid: String,
        val amount: Int,
        val receipt: String,
        val description: String,
        val status: String,
        val reject_reason: String,
        val created_dateime: String
    )
}