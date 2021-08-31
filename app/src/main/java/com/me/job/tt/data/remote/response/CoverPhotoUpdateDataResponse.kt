package com.me.job.tt.data.remote.response

import java.io.Serializable

data class CoverPhotoUpdateDataResponse(
    val name: String,
    var url: String
):Serializable