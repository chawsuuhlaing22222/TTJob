package com.me.job.tt.viewHolder

import android.view.View
import com.me.job.tt.ui.delegate.ShowCoverPhotoDelegate
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.image_entry.view.*

class ShowCoverPhotoViewHolder(var mView: View, var delegate:ShowCoverPhotoDelegate):BaseViewHolder<String>(mView) {
    override fun setData(data: String) {

       Picasso.get().load(data).into(mView.iv_cover_photo)

        mView.ib_delete.setOnClickListener {
            delegate.coverPhotoClick(data)
        }
    }

    override fun onClick(p0: View?) {


    }
}