package com.me.job.tt.data.remote.request

import java.io.Serializable

data class FoundNameUpdateRequest(
    val id:String,
    val name:String
):Serializable
