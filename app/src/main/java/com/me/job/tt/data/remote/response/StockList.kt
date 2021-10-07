package com.me.job.tt.data.remote.response

data class StockList(
    val id: String,
    val name: String,
    val pointTypeId: String,
    val pointTypeName: String,
    val stockList: List<StockItem>
)