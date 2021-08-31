package com.me.job.tt.data.remote.response

import java.io.Serializable

data class FoundProfCoverPhoUpdateResponse(
    val `data`: List<CoverPhotoUpdateDataResponse>,
    val error: Any,
    val message: String,
    val paging: Any,
    val success: Boolean
):Serializable