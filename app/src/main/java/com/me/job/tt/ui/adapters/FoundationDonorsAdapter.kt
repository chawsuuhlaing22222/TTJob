package com.me.job.tt.ui.adapters

import android.content.Context
import android.view.ViewGroup
import com.android.tintoung.ui.delegates.FoundationDonorsDelegate
import com.me.job.R
import com.me.job.tt.data.remote.response.FoundationDonors
import com.me.job.tt.viewHolder.BaseViewHolder
import com.me.job.tt.viewHolder.FoundationDonorsViewHolder

class FoundationDonorsAdapter(
    context: Context,
    val delegate: FoundationDonorsDelegate
) :
    BaseRecyclerAdapter<FoundationDonorsViewHolder, FoundationDonors.Data>(
        context
    ) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<FoundationDonors.Data> {
        val view = mLayoutInflator.inflate(R.layout.view_holder_foundatin_donors, parent, false)
        return FoundationDonorsViewHolder(view, delegate)
    }

}