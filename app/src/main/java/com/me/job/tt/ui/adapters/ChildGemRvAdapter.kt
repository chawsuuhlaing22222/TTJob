package com.me.job.tt.ui.adapters

import android.content.Context
import android.view.ViewGroup
import com.me.job.R
import com.me.job.tt.data.remote.response.StockItem
import com.me.job.tt.ui.delegate.ChildGemDelegate
import com.me.job.tt.viewHolder.BaseViewHolder
import com.me.job.tt.viewHolder.ChildGemViewHolder

class ChildGemRvAdapter(var childGemDelegate: ChildGemDelegate, context: Context):BaseRecyclerAdapter<ChildGemViewHolder,StockItem>(
    context
) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<StockItem> {
        var childview=mLayoutInflator.inflate(R.layout.view_gem_holder,parent,false)
        return ChildGemViewHolder(childview,childGemDelegate)
    }
}