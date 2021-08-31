package com.me.job.tt.data.remote.response

import com.me.job.tt.data.vos.Paging
import java.io.Serializable

data class FoundationBasicInfoUpdateResponse(
    val data: FoundationBasicInfoUpdateData?,
    val error: Boolean,
    val message: String,
    val paging: Paging?,
    val success: Boolean
):Serializable