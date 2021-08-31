package com.me.job.tt.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.me.job.R
import com.smarteist.autoimageslider.SliderViewAdapter
import com.squareup.picasso.Picasso

class ImageSliderAdapter:SliderViewAdapter<ImageSliderAdapter.VH>() {

    private var mSliderItems = ArrayList<String>()
    fun renewItems(sliderItems: ArrayList<String>) {
        mSliderItems = sliderItems
        notifyDataSetChanged()
    }

    fun addItem(sliderItem: String) {
        mSliderItems.add(sliderItem)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup): VH {
        val inflate: View = LayoutInflater.from(parent.context).inflate(R.layout.slider_image, null)
        return VH(inflate)
    }

    override fun onBindViewHolder(viewHolder: VH, position: Int) {
        //load image into view
        //this is from url to byte
        //viewHolder.imageView.setImageBitmap(Picasso.get().load(mSliderItems[position]).get())

        //this is from url
        Picasso.get().load(mSliderItems[position]).fit().into(viewHolder.imageView)



        /*Picasso.get().load(mSliderItems[position]).resize(500, 100)
            .noFade().into(viewHolder.imageView)*/
       // Picasso.with(this).load(img_url).placeholder(R.drawable.user_image)// Place holder image from drawable folder
        //   .error(R.drawable.user_image).resize(110, 110).centerCrop()
         //   .into("IMAGE VIEW ID WHERE YOU WANT TO SET IMAGE");
    }

    override fun getCount(): Int {
        return mSliderItems.size
    }

    inner class VH(itemView: View) : ViewHolder(itemView) {
        var imageView: ImageView = itemView.findViewById(R.id.iv_imageSlier)

    }
}