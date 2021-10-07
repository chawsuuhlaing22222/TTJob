package com.me.job.tt.data.remote.response

import android.os.Parcel
import android.os.Parcelable

data class CurrencyExchangeData(
    val countryId: String,
    val countryLogo: String,
    val currencyCode: String,
    val currencyId: String,
    val currencyName: String,
    val exchangeRate: Double,
    val exchangeRateId: String
)