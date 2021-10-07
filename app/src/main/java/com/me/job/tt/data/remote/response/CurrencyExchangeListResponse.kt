package com.me.job.tt.data.remote.response

import java.io.Serializable
import java.util.ArrayList

data class CurrencyExchangeListResponse(
    val `data`: ArrayList<CurrencyExchangeData>,
    val error: Any,
    val message: Any,
    val paging: Any,
    val success: Boolean
)