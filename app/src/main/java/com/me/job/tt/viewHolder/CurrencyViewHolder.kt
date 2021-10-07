package com.me.job.tt.viewHolder

import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.me.job.tt.data.remote.response.CurrencyExchangeData
import com.me.job.tt.data.remote.response.StockItem
import com.me.job.tt.data.remote.response.StockList
import com.me.job.tt.ui.adapters.ChildGemRvAdapter
import com.me.job.tt.ui.delegate.ChildGemDelegate
import com.me.job.tt.ui.delegate.CurrencyFragmentDelegate
import com.squareup.picasso.Picasso

import kotlinx.android.synthetic.main.currency_list.view.*

class CurrencyViewHolder(var view: View, var delegate:CurrencyFragmentDelegate,var childDelegate:ChildGemDelegate):
    BaseViewHolder<StockList>(view) {
    lateinit var childGemAdapter:ChildGemRvAdapter
    var clickData:StockList?=null
    override fun setData(data: StockList) {

        clickData=data
        childGemAdapter= ChildGemRvAdapter(childDelegate,view.context)
        childGemAdapter.setNewData(data.stockList as MutableList<StockItem>)
         view.tv_gem_coin.text=data.pointTypeName

        view.rv_gem.adapter=childGemAdapter
        view.rv_gem.layoutManager=GridLayoutManager(view.context,2)

    }

    override fun onClick(p0: View?) {

        clickData?.let { delegate.onClick(it) }

    }
}