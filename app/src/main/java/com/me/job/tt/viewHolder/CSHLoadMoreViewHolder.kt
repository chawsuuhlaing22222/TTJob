package com.me.job.tt.viewHolder

import android.view.View
import kotlinx.android.synthetic.main.item_load_more_holder.view.*

class CSHLoadMoreViewHolder(itemView: View):BaseViewHolder<String>(itemView) {
    override fun setData(data: String) {
        itemView.tv_item.text=data
    }

    override fun onClick(p0: View?) {
        TODO("Not yet implemented")
    }
}