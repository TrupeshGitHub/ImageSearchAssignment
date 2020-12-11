package com.assignment.imagesearch.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.assignment.imagesearch.R
import com.assignment.imagesearch.model.Data
import kotlinx.android.synthetic.main.list_item_image.view.*

class ImageListAdapter(
    private val context: Context,
    private val onClickListener: OnClickListener
) :
    RecyclerView.Adapter<ImageListAdapter.ViewHolder>() {

    private var imageSearchList = emptyList<Data>()

    /**
     * Set Updated list to adapter and notify Adapter
     *
     * @param list
     */
    fun setData(list: List<Data>) {
        imageSearchList = list
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val searchImage: ImageView = view.searchImage

        init {
            searchImage.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onClickListener.onClick(imageSearchList[position], position)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.list_item_image,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = imageSearchList[position]
        loadImage(holder.searchImage, data)
    }


    /**
     * Load image from URL using glide
     *
     * @param userImage
     * @param data
     */
    private fun loadImage(userImage: ImageView?, data: Data) {
        if (data.images?.isNotEmpty() == true) {
            userImage?.let {
                Glide.with(context).load(data.images[0].link)
                    .format(DecodeFormat.PREFER_ARGB_8888)
                    .override(100, 100)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.progress_animation)
                    .into(it)
            }
        }
    }

    override fun getItemCount(): Int = imageSearchList.size

    /**
     * RecyclerView Click Listeners
     *
     */
    interface OnClickListener {
        fun onClick(data: Data, position: Int)
    }

}