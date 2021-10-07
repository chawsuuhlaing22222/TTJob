package com.me.job.tt.viewHolder

import android.view.View

import com.android.tintoung.ui.delegates.FoundationDonorsDelegate
import com.bumptech.glide.Glide
import com.me.job.tt.data.remote.response.FoundationDonors
import kotlinx.android.synthetic.main.view_holder_foundatin_donors.view.*

class FoundationDonorsViewHolder(
    itemView: View,
    val delegate: FoundationDonorsDelegate
) :
    BaseViewHolder<FoundationDonors.Data>(itemView) {

    override fun onClick(v: View?) {
        delegate.onItemClick(mData)
    }


    override fun setData(data: FoundationDonors.Data) {
        mData = data
        if(mData.donationtype != null){
            itemView.rlacceptreject.visibility = if (mData.donationtype == 2) View.VISIBLE else View.GONE
        }
        itemView.btaccept.setOnClickListener{
            delegate.onAccessRejectClick(mData)
        }
        itemView.btnreject.setOnClickListener{
            delegate.onAccessRejectClick(mData)
        }
        itemView.tvName.text = mData.username
        itemView.tvMember.text = "Member"
        itemView.tvamount.text = mData.amount.toString() +" MMK"
        itemView.tvdesc.text = "Postal codes in Myanmar are five digit numbers. The first two digits of the postal code denote the States, Regions, and Union Territories. Listed below are the first 2 digits of the codes assigned to each state and region.Postal codes in Myanmar are five digit numbers. The first two digits of the postal code denote the States, Regions, and Union Territories. Listed below are the first 2 digits of the codes assigned to each state and region.Postal codes in Myanmar are five digit numbers. The first two digits of the postal code denote the States, Regions, and Union Territories. Listed below are the first 2 digits of the codes assigned to each state and region."
        Glide.with(itemView.context).load(mData.userphoto).into(itemView.ivProfile)
    }

}