package com.me.job.tt.data.remote.response

import java.io.Serializable

data class Videos(
    val covervideos:List<String>,
    val profilevideos: List<String>,
    val uploadvideos: List<String>): Serializable