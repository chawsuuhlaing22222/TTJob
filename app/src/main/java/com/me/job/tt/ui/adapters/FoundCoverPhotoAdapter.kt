package com.me.job.tt.ui.adapters

import android.graphics.Bitmap
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.me.job.R
import kotlinx.android.synthetic.main.image_entry.view.*




class FoundCoverPhotoAdapter(val dataset:List<Int>):RecyclerView.Adapter<FoundCoverPhotoAdapter.ViewHolder>()   {
    private var onClickListener: OnClickListener? = null
    var selectedItem=0
   class ViewHolder(view:View):RecyclerView.ViewHolder(view){

       var ivCoverPhoto:ImageView
       var cvCoverPhoto:CardView
       init {
           ivCoverPhoto=view.findViewById(R.id.iv_cover_photo)
           cvCoverPhoto=view.findViewById(R.id.cv_cover_photo)
       }

   }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FoundCoverPhotoAdapter.ViewHolder {
       var view:View=LayoutInflater.from(parent.context)
           .inflate(R.layout.image_entry,parent,false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: FoundCoverPhotoAdapter.ViewHolder, position: Int) {
        holder.ivCoverPhoto.setImageResource(dataset.get(position))

        holder.cvCoverPhoto.setCardBackgroundColor(
            Color.WHITE
        )

        if (selectedItem === position) {
            holder.cvCoverPhoto.setCardBackgroundColor(
               Color.BLUE
            )
        }

        holder.itemView.setOnClickListener {
            val previousItem: Int = selectedItem
            selectedItem = position
            notifyItemChanged(previousItem)//Notify any registered observers that the item at position has changed.
            notifyItemChanged(position)//Notify any registered observers that the item at position has changed.

            if (onClickListener != null){
                onClickListener!!.onClick()
            }
        }


        /*holder.ivCoverPhoto.setOnClickListener {
            if (onClickListener != null){
                onClickListener!!.onClick()
            }
        }*/

    }

    override fun getItemCount(): Int {
      return dataset.size
    }

    interface OnClickListener {
        fun onClick()
    }

    fun setOnClickListener(onClickListener: OnClickListener){
        this.onClickListener = onClickListener
    }

}