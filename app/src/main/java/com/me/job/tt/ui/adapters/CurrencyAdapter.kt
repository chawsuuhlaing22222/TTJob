package com.me.job.tt.ui.adapters

import android.content.Context
import android.view.ViewGroup
import com.me.job.R
import com.me.job.tt.data.remote.response.StockList
import com.me.job.tt.ui.delegate.ChildGemDelegate
import com.me.job.tt.ui.delegate.CurrencyFragmentDelegate
import com.me.job.tt.viewHolder.BaseViewHolder
import com.me.job.tt.viewHolder.CurrencyViewHolder

class CurrencyAdapter(var context: Context, var delegate:CurrencyFragmentDelegate,var childGemdelegate:ChildGemDelegate)
    :BaseRecyclerAdapter<CurrencyViewHolder,StockList>(context){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<StockList> {
        val view = mLayoutInflator.inflate(R.layout.currency_list, parent, false)
        return CurrencyViewHolder(view,delegate,childGemdelegate)
    }
}