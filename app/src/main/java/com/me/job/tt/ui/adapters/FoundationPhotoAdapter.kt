package com.me.job.tt.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.me.job.R
import kotlinx.android.synthetic.main.view_item_foundation_photo.view.*


class FoundationPhotoAdapter(private val c: Context, private val images: ArrayList<String>) :
    RecyclerView.Adapter<FoundationPhotoAdapter.FoundationViewHolder>() {


    override fun getItemCount(): Int {
        return images.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoundationViewHolder {
        return FoundationViewHolder(LayoutInflater.from(c).inflate(R.layout.view_item_foundation_photo, parent, false))
    }

    override fun onBindViewHolder(holder: FoundationViewHolder, position: Int) {
        val path = images[position]

        if (images != null) {
            Glide.with(c)
                .load(path)
                .placeholder(R.drawable.placeholder)
                .into(holder.ivfoundationprofile)
        }

        holder.ivfoundationprofile.setOnClickListener {
            //handle click event on image
        }
    }

    class FoundationViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivfoundationprofile = view.ivfoundationprofile as ImageView
    }
}