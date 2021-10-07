package com.me.job.tt.ui.adapters

import android.content.Context
import android.view.ViewGroup
import com.me.job.R
import com.me.job.tt.viewHolder.BaseViewHolder
import com.me.job.tt.viewHolder.CSHLoadMoreViewHolder
import com.me.job.tt.viewHolder.FoundationDonorsViewHolder

class CSHLoadMoreAdapter(context: Context):
BaseRecyclerAdapter<CSHLoadMoreViewHolder, String>(
context
)  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<String> {
        val view = mLayoutInflator.inflate(R.layout.item_load_more_holder, parent, false)
        return CSHLoadMoreViewHolder(view)
    }
}