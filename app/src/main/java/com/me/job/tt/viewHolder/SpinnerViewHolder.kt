package com.me.job.tt.viewHolder

import android.view.View
import com.me.job.tt.data.remote.response.CurrencyExchangeData
import com.me.job.tt.ui.delegate.SpinnerDelegate
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.spinner_drop_down.view.*

class SpinnerViewHolder(var itemView: View,var spinnerDelegate:SpinnerDelegate) :
    BaseViewHolder<CurrencyExchangeData>(itemView) {
    override fun setData(data: CurrencyExchangeData) {

            Picasso.get().load(data.countryLogo).fit().into(itemView.iv_country_logo)
        itemView.tv_currencyCode.text=data.currencyCode
        itemView.tv_currencyName.text=data.currencyName

    }

    override fun onClick(p0: View?) {
        TODO("Not yet implemented")
    }
}