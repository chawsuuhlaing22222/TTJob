package com.me.job.tt.data.remote.request

data class CommentRequest(
    val message: String,
    val commentid: String,
    val userguid: String,
    val feedid: String,
    val parent_commentid: String
)