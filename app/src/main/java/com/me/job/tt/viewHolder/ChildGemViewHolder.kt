package com.me.job.tt.viewHolder
import android.annotation.SuppressLint
import android.view.View
import com.me.job.R
import com.me.job.tt.data.remote.response.StockItem
import com.me.job.tt.ui.delegate.ChildGemDelegate
import kotlinx.android.synthetic.main.view_gem_holder.view.*

class ChildGemViewHolder(var mView:View,var childGemDelegate: ChildGemDelegate): BaseViewHolder<StockItem>(mView) {

    var childItem:StockItem?=null
    override fun setData(data: StockItem) {

        childItem=data
        mView.tv_gem_qty.text=data.quantity.toString()
        mView.tv_mmk_price.text=data.price.toString()

    }

    @SuppressLint("ResourceAsColor")
    override fun onClick(p0: View?) {
        childItem?.let { childGemDelegate.childGemOnClik(it) }

        if (p0 != null) {
            p0.setBackgroundColor(R.color.gold)
        }
    }


}