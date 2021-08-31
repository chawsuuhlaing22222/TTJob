package com.me.job.tt.data.remote.request

import java.io.Serializable

data class FoundationProfileBasicInfo(

    val id:String,
    val foundationEmail:String,
    val foundationPhone:String,
    val foundationPostalCode:String,
    val foundationLocation:String,
    val foundationHistory:String,
):Serializable