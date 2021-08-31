package com.me.job.tt.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.me.job.R
import com.me.job.tt.data.vos.CoverPhoto
import kotlinx.android.synthetic.main.image_entry.view.*

class FCoverPhotoAdapter:BaseAdapter {
    var coverPhotoList:ArrayList<CoverPhoto>?=null
    var context: Context? = null
    var pos:Int=0

    private var onClickListener: OnClickListener? = null

    constructor(context: Context, coverPhotoList: ArrayList<CoverPhoto>):super() {
        this.context = context
        this.coverPhotoList = coverPhotoList
    }

    override fun getCount(): Int {
        return coverPhotoList!!.count()

    }

    override fun getItem(p0: Int): Any {
        return coverPhotoList!![p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, view: View?, viewGroup: ViewGroup?): View {

        pos=p0
        val coverPhoto: CoverPhoto?= this.coverPhotoList?.get(p0)
        var inflator=context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var coverView = inflator.inflate(R.layout.image_entry, null)
        //coverPhoto.image?.let { coverView.iv_cover_photo.setImageResource(it) }
        // coverView.iv_cover_photo.setImageResource(coverPhoto?.image!!)

        coverView.ib_delete.setOnClickListener {

            coverPhotoList?.remove(coverPhoto)
            notifyDataSetChanged()
        }



        coverView.iv_cover_photo.setImageBitmap(coverPhoto?.image)
        return coverView


    }

    interface OnClickListener {
        fun onClick()
    }

    fun setOnClickListener(onClickListener: OnClickListener){
        this.onClickListener = onClickListener
    }



}