package com.me.job.tt.ui.adapters

import android.content.Context
import android.view.ViewGroup
import com.me.job.R
import com.me.job.tt.ui.delegate.ShowCoverPhotoDelegate
import com.me.job.tt.viewHolder.BaseViewHolder
import com.me.job.tt.viewHolder.ShowCoverPhotoViewHolder

class ShowCoverPhotoAdapter(var context: Context,var mdelegate:ShowCoverPhotoDelegate):BaseRecyclerAdapter<ShowCoverPhotoViewHolder,String>(context) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<String> {
        var view=mLayoutInflator.inflate(R.layout.image_entry,parent,false)
        return ShowCoverPhotoViewHolder(view,mdelegate)
    }
}