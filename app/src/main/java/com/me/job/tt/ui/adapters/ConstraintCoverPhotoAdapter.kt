package com.me.job.tt.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.me.job.R
import com.me.job.tt.data.remote.response.CoverPhotoUpdateDataResponse
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.image_entry.view.*

class ConstraintCoverPhotoAdapter: BaseAdapter {

    var coverPhotoList:ArrayList<CoverPhotoUpdateDataResponse>
    var context: Context? = null

    constructor(context: Context, coverPhotoList: ArrayList<CoverPhotoUpdateDataResponse>):super() {
        this.context = context
        this.coverPhotoList = coverPhotoList
    }
    override fun getCount(): Int {
        return coverPhotoList!!.size
    }

    override fun getItem(p0: Int): Any {
        return coverPhotoList!![p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var coverPhoto:CoverPhotoUpdateDataResponse= this.coverPhotoList.get(p0)
        var inflator=context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var coverView = inflator.inflate(R.layout.image_entry, null)
        //coverPhoto.image?.let { coverView.iv_cover_photo.setImageResource(it) }
        // coverView.iv_cover_photo.setImageResource(coverPhoto?.image!!)
        //this is from url

        coverView.ib_delete.setOnClickListener{

            coverPhoto=CoverPhotoUpdateDataResponse("img1",
                "https://amhantayar-dev.sgp1.digitaloceanspaces.com/Question/2422ae8f-90fd-4f7c-b2e7-76155deb3720_8222021052756AM_fp.jpg")
            coverPhotoList?.removeAt(p0)
            coverPhotoList?.add(p0, coverPhoto)
            notifyDataSetChanged()

        }

        Picasso.get().load(coverPhoto?.url).fit().into(coverView.iv_cover_photo)
        return coverView
    }
}