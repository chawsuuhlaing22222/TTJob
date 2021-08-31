package com.me.job.tt.data.remote.response

data class BankAccount(
    val account_no: String,
    val created_datetime: String,
    val id: String,
    val name: String,
    val photo: String,
    val photo_base64: Any
)