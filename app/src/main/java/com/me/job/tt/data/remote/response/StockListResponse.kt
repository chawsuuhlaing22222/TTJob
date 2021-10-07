package com.me.job.tt.data.remote.response

import com.me.job.tt.data.vos.Paging

data class StockListResponse(
    val `data`: Data,
    val error: Any,
    val message: Any,
    val paging: Paging,
    val success: Boolean
) {
    data class Data(
        val currentCurrency: CurrentCurrency,
        val items: List<StockList>
    )

}