package com.me.job.tt.data.remote.response

import java.io.Serializable

data class DonationPost(
    val _isdonation: Boolean,
    val bankaccounts: List<String>,
    val categoryid: String,
    val comment_count: Int,
    val created_datetime: String,
    val currenttamount: Int,
    val description: String,
    val description_mm: String,
    val filetype: Int,
    val foundationid: String,
    val foundationname: String,
    val foundationphoto: String,
    val id: String,
    val is_liked: Boolean,
    val is_loved: Boolean,
    val is_viewed: Boolean,
    val like_count: Int,
    val location: String,
    val love_count: Int,
    val photos: List<String>,
    val targetamount: Int,
    val title: String,
    val title_mm: String,
    val video: String,
    val video_duration: Int,
    val videocoverphoto: String,
    val view_count: Int
): Serializable