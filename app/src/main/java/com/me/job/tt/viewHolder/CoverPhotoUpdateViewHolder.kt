package com.me.job.tt.viewHolder

import android.view.View
import com.me.job.R
import com.me.job.tt.data.remote.response.CoverPhotoUpdateDataResponse
import com.me.job.tt.ui.delegate.EditCoverPhotoDelegate
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.image_entry.view.*

class CoverPhotoUpdateViewHolder(itemView: View, var mdelegate:EditCoverPhotoDelegate):BaseViewHolder<CoverPhotoUpdateDataResponse>(itemView) {
    override fun setData(data: CoverPhotoUpdateDataResponse) {

        if(data !=null){
            Picasso.get().load(data.url).placeholder(R.drawable.image).fit().into(itemView.iv_cover_photo)
        }



    }

    override fun onClick(p0: View?) {


    }
}