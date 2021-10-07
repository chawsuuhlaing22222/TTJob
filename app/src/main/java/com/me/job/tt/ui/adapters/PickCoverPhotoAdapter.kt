package com.me.job.tt.ui.adapters

import android.content.Context
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.me.job.R
import com.me.job.tt.data.remote.request.FoundCoverPhotoUpdateRequest
import com.me.job.tt.data.remote.request.UpdatePhoto
import com.me.job.tt.data.remote.response.CoverPhotoUpdateDataResponse
import com.me.job.tt.ui.delegate.PickCoverPhotoDelegate
import com.me.job.tt.viewHolder.BaseViewHolder
import com.me.job.tt.viewHolder.PickCoverPhotoViewHolder
import kotlinx.android.synthetic.main.image_entry.view.*
import kotlinx.android.synthetic.main.image_entry.view.iv_cover_photo
import kotlinx.android.synthetic.main.pickcoverphoto_entry.view.*

class PickCoverPhotoAdapter(var context: Context,var mdelegate: PickCoverPhotoDelegate):BaseRecyclerAdapter<PickCoverPhotoViewHolder, String>(context) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<String> {
        var mView=mLayoutInflator.inflate(R.layout.pickcoverphoto_entry,parent,false)
        return PickCoverPhotoViewHolder(mView,mdelegate)
    }


  /*  override fun onBindViewHolder(
        holder: BaseViewHolder<String>,
        position: Int
    ) {
        super.onBindViewHolder(holder, position)
        holder.itemView.iv_cover_photo.setOnClickListener {

           if(holder.itemView.iv_select.visibility==View.GONE){
               holder.itemView.iv_select.visibility=View.VISIBLE
           }else{
               holder.itemView.iv_select.visibility=View.GONE
           }



            //mdelegate.onCoverPhotoClick(mData!!.get(position))
            notifyDataSetChanged()
            //mdelegate.onClick(position)
        }

    }*/
}