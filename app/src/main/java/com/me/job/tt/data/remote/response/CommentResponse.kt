package com.me.job.tt.data.remote.response


import com.me.job.tt.data.vos.AllComment
import com.me.job.tt.data.vos.Paging

data class CommentResponse(
    val success: Boolean,
    val error: Error,
    val data: AllComment,
    val paging: Paging
)