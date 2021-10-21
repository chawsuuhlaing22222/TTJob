package com.me .job.tt.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.me.job.R
import com.me.job.tt.ui.delegate.PickCoverPhotoDelegate
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.pickcoverphoto_entry.view.*

class PickCoverPhotoSwipeAdapter(var context: Context,var pickCoverPhotoDelegate: PickCoverPhotoDelegate)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items:ArrayList<String?>? = null
    var selected=0
    val VIEW_ITEM_ROW=1
    val VIEW_LOAD=0

   fun setData(itemList: ArrayList<String?>,flag:Int){
        items = arrayListOf()
        items = itemList
        selected=flag
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

       return when(viewType){
            VIEW_ITEM_ROW->{

                    var view=LayoutInflater.from(parent.context).inflate(R.layout.pickcoverphoto_entry,parent,false)
                     ItemViewHolder(view)
                }

            VIEW_LOAD->{
                var view=LayoutInflater.from(parent.context).inflate(R.layout.view_item_loading,parent,false)
                 ShowLoading(view)
            }
           else -> {
               var view=LayoutInflater.from(parent.context).inflate(R.layout.view_item_loading,parent,false)
               ShowLoading(view)
           }
       }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

           when(holder){
              is PickCoverPhotoSwipeAdapter.ItemViewHolder->populateItemView(holder,position)
              is PickCoverPhotoSwipeAdapter.ShowLoading->showLoadingView(holder,position)

           }
    }

    override fun getItemCount(): Int {

        return  if(items==null) 0 else items!!.size

    }

    override fun getItemViewType(position: Int): Int {
        when(items?.get(position)){
            null->{return VIEW_LOAD}
            else->return VIEW_ITEM_ROW
        }

    }

    inner class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        var iv_cover_photo=itemView.iv_cover_photo
        var iv_select=itemView.iv_select

    }

    inner class ShowLoading(itemView: View):RecyclerView.ViewHolder(itemView)

    private fun showLoadingView(viewHolder: ShowLoading, position: Int) {
        //ProgressBar would be displayed
    }

    fun populateItemView(holder:ItemViewHolder,position: Int){

        val mData = items!![position]
        mData?.let {

            Picasso.get().load(mData).into(holder.iv_cover_photo)

            if(selected==0){
                holder.iv_cover_photo.setOnClickListener {
                    //Toast.makeText(,"View Image",Toast.LENGTH_SHORT).
                    pickCoverPhotoDelegate.onClickPhoto()
                }
            }

            if(selected==1){

                holder.iv_cover_photo.alpha=0.4F
                holder.iv_cover_photo.setOnClickListener {

                    if(holder.iv_select.visibility==View.GONE){
                        holder.iv_cover_photo.alpha=1F
                        holder.iv_select.visibility=View.VISIBLE
                        pickCoverPhotoDelegate.onSelectPhoto(mData)

                    }else{
                        pickCoverPhotoDelegate.onUnselectPhoto(mData)
                        holder.iv_select.visibility=View.GONE
                        holder.iv_cover_photo.alpha=0.4F
                    }
                }
            }


        }


    }
}