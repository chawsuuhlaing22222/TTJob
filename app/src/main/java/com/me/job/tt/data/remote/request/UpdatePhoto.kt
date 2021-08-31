package com.me.job.tt.data.remote.request

import java.io.Serializable

data class UpdatePhoto(
    val photo_name:String,
    val photo_base64:String,
    val photo_extension:String
):Serializable
