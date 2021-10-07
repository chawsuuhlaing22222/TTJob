package com.me.job.tt.viewHolder

import android.opengl.Visibility
import android.view.View
import androidx.core.view.isVisible
import com.me.job.tt.data.remote.request.FoundCoverPhotoUpdateRequest
import com.me.job.tt.data.remote.request.UpdatePhoto
import com.me.job.tt.data.remote.response.CoverPhotoUpdateDataResponse
import com.me.job.tt.ui.delegate.PickCoverPhotoDelegate
import com.me.job.tt.utils.AppConstants
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.pickcoverphoto_entry.view.*

class PickCoverPhotoViewHolder(var mView:View,var mdelegate:PickCoverPhotoDelegate):BaseViewHolder<String>(mView){


    override fun setData(data: String) {

        Picasso.get().load(data).into(mView.iv_cover_photo)

        mView.iv_cover_photo.setOnClickListener {

            if(mView.iv_select.visibility==View.GONE){

                mView.iv_select.visibility=View.VISIBLE
                mdelegate.onSelectPhoto(data)

            }else{
                mdelegate.onUnselectPhoto(data)
                mView.iv_select.visibility=View.GONE
            }
        }

    }

    override fun onClick(p0: View?) {
        TODO("Not yet implemented")
    }


}