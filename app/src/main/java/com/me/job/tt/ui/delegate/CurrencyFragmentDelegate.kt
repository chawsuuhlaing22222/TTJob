package com.me.job.tt.ui.delegate

import com.me.job.tt.data.remote.response.CurrencyExchangeData
import com.me.job.tt.data.remote.response.StockList

interface CurrencyFragmentDelegate {
    fun onClick(data:StockList)
}