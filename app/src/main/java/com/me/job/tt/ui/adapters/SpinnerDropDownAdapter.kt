package com.me.job.tt.ui.adapters

import android.content.Context
import android.database.DataSetObserver
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.me.job.R
import com.me.job.tt.data.remote.response.CurrencyExchangeData
import com.me.job.tt.data.vos.CoverPhoto
import com.me.job.tt.ui.delegate.SpinnerDelegate
import com.me.job.tt.viewHolder.BaseViewHolder
import com.me.job.tt.viewHolder.SpinnerViewHolder
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_currency.view.*
import kotlinx.android.synthetic.main.image_entry.view.*
import kotlinx.android.synthetic.main.spinner_drop_down.view.*

class SpinnerDropDownAdapter(var context: Context,var data:List<CurrencyExchangeData>?):
    BaseAdapter(){
    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    lateinit var listener: OnItemSelectedListener
    override fun getCount(): Int {

        data?.let {
            return it.size
        }
       return 0
    }

    override fun getItem(p0: Int): Any {

        return data?.get(p0)!!
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val currencyData: CurrencyExchangeData?= this.data?.get(position)
        val view: View
        val vh: ItemHolder
        if (convertView == null) {
            view = inflater.inflate(R.layout.spinner_drop_down, parent, false)
            vh = ItemHolder(view)
            view?.tag = vh

        } else {
            view = convertView
            vh = view.tag as ItemHolder
        }
        if (currencyData != null) {
            Picasso.get().load(currencyData.countryLogo).fit().into(vh.country_logo)
            vh.currency_code.text = currencyData.currencyCode
            vh.currency_name.text=currencyData.currencyName
        }



        return view


        /*
        val currencyData: CurrencyExchangeData?= this.data?.get(p0)
        var inflator=context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var spinnerView = inflator.inflate(R.layout.spinner_drop_down, null)
        //coverPhoto.image?.let { coverView.iv_cover_photo.setImageResource(it) }
        // coverView.iv_cover_photo.setImageResource(coverPhoto?.image!!)

        if (currencyData != null) {
            Picasso.get().load(currencyData.countryLogo).fit().into(spinnerView.iv_country_logo)
            spinnerView.tv_currencyCode.text=currencyData.currencyCode
            spinnerView.tv_currencyName.text=currencyData.currencyName
        }else{
            spinnerView.iv_country_logo.setImageResource(R.drawable.empty)
        }

       // spinnerView.spinner.setSelection(1)
       //spinnerView.isSelected=true

        return spinnerView */
    }

    fun setOnItemClickListener(listener: OnItemSelectedListener) {
        this.listener = listener
    }


    interface OnItemSelectedListener {
        fun onClick()
    }

    private class ItemHolder(row: View?) {
        var currency_code: TextView
        var currency_name: TextView
        var country_logo: ImageView

        init {
            currency_code = row?.findViewById(R.id.tv_currencyCode) as TextView
            currency_name= row?.findViewById(R.id.tv_currencyName) as TextView
            country_logo = row?.findViewById(R.id.iv_country_logo) as ImageView
        }
    }

}


/*class SpinnerDropDownAdapter(var context: Context,var data:List<CurrencyExchangeData>?,var spinnerDelegate:SpinnerDelegate):
     SpinnerAdapter {
    override fun registerDataSetObserver(p0: DataSetObserver?) {
        TODO("Not yet implemented")
    }

    override fun unregisterDataSetObserver(p0: DataSetObserver?) {
        TODO("Not yet implemented")
    }


    override fun getCount(): Int {
       return data?.size!!
    }

    override fun getItem(p0: Int): CurrencyExchangeData? {
       return data?.get(p0)
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }


    override fun hasStableIds(): Boolean {
        TODO("Not yet implemented")
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val currencyData: CurrencyExchangeData?= getItem(p0)
        var inflator=context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var spinnerView = inflator.inflate(R.layout.spinner_layout, null)
        //coverPhoto.image?.let { coverView.iv_cover_photo.setImageResource(it) }
        // coverView.iv_cover_photo.setImageResource(coverPhoto?.image!!)

        if (currencyData != null) {
            Picasso.get().load(currencyData.countryLogo).fit().into(spinnerView.iv_country_logo)
            spinnerView.tv_currencyCode.text=currencyData.currencyCode
            spinnerView.tv_currencyName.text=currencyData.currencyName
        }else{
            spinnerView.iv_country_logo.setImageResource(R.drawable.empty)
        }

        // spinnerView.spinner.setSelection(1)
        //spinnerView.isSelected=true

        return spinnerView
    }

    override fun getItemViewType(p0: Int): Int {
        TODO("Not yet implemented")
    }

    override fun getViewTypeCount(): Int {
        TODO("Not yet implemented")
    }

    override fun isEmpty(): Boolean {
        TODO("Not yet implemented")
    }

    override fun getDropDownView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val currencyData: CurrencyExchangeData?= getItem(p0)
        var inflator=context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var dropdownView = inflator.inflate(R.layout.spinner_drop_down, null)
        //coverPhoto.image?.let { coverView.iv_cover_photo.setImageResource(it) }
        // coverView.iv_cover_photo.setImageResource(coverPhoto?.image!!)

        if (currencyData != null) {
            Picasso.get().load(currencyData.countryLogo).fit().into(dropdownView.iv_country_logo)
            dropdownView.tv_currencyCode.text=currencyData.currencyCode
            dropdownView.tv_currencyName.text=currencyData.currencyName
        }else{
            dropdownView.iv_country_logo.setImageResource(R.drawable.empty)
        }

        // spinnerView.spinner.setSelection(1)
        //spinnerView.isSelected=true

        return dropdownView
    }


}*/