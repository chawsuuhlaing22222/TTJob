package com.me.job.tt.data.remote.response

import java.io.Serializable

data class Photos (
val coverphotos: List<String>,
val profilephotos: List<String>,
val uploadphotos: List<String>
): Serializable