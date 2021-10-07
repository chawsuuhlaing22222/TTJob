package com.me.job.tt.data.remote.response

data class CurrentCurrency(
    val currencyCode: String,
    val currencyId: String,
    val currencyName: String,
    val exchageRate: Double,
    val exchangeRateId: String
)