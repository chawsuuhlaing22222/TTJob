package com.me.job.tt.data.remote.request

import java.io.Serializable

data class FoundationProfileBasicInfoUpdateRequest(

    val id: String,
    val foundation_email: String,
    val foundation_phoneno: String,
    val postalcode: String,
    val location: String,
    val history: String

):Serializable