package com.me.job.tt.data.remote.response

import com.me.job.tt.data.vos.Paging
import java.io.Serializable

data class CoverPhotoListResponse(
    val `data`: List<String>,
    val error: Boolean,
    val message: Any,
    val paging: Paging,
    val success: Boolean
) :Serializable