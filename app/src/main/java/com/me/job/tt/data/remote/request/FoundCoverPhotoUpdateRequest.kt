package com.me.job.tt.data.remote.request

import java.io.Serializable

data class FoundCoverPhotoUpdateRequest(
    val id:String,
    val photo_list:ArrayList<UpdatePhoto>
):Serializable
