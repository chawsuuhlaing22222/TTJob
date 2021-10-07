package com.me.job.tt.data.remote.request

import java.io.Serializable

data class ThemeCoverPhotoUpdateRequest(

    val id: String,
    val foundationuserid: String,
    val theme: String,
    val photo_list: List<String>

):Serializable