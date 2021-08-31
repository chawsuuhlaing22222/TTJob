package com.me.job.tt.data.remote.request

import java.io.Serializable

data class FoundWebsiteUpdateRequest(
    val id:String,
    val website:String
):Serializable