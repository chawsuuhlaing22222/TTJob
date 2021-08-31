package com.me.job.tt.data.vos

import java.io.Serializable


data class AllComment(
    var childcomments: List<AllComment>,
    var childcomments_count: Int,
    val comment_count: Int,
    val commentid: String,
    val created_time: String,
    val created_time_uom: String,
    var message: String,
    val parent_commentid: String,
    val user_photo: String,
    val userid: String,
    val username: String
) : Serializable {
}