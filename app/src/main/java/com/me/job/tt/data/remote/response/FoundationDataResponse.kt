package com.me.job.tt.data.remote.response

import com.me.job.tt.data.vos.Paging
import java.io.Serializable

data class FoundationDataResponse(
    val success:Boolean,
    val message:String?=null,
    val data:FoundationProfileResponse?,
    val paging:Paging?,
    val error:Boolean) : Serializable