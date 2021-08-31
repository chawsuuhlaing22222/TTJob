package com.me.job.tt.data.vos

import com.google.gson.annotations.SerializedName
import java.io.Serializable

//foundation id

class BankInfoVos (
    @SerializedName("id")
    var  id: String,
    @SerializedName("name")
    var name: String,
    @SerializedName("photo")
    var photo: String,
    @SerializedName("created_datetime")
    var created_datetime: String,
    var account_no: String,
    var photo_base64: String

): Serializable

