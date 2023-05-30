package com.example.homework2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.bumptech.glide.Glide
import com.example.homework2.databinding.ItemImageBinding
import com.example.homework2.model.Photo

class ImageAdapter(
    private val list: ArrayList<Photo>,
    private val clickListener: (Photo) -> Unit,
    private val deleteItem: (Photo) -> Unit,
) : Adapter<ImageAdapter.ImageViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(ItemImageBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ImageViewHolder(private var binding: ItemImageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(image: Photo) {
            binding.vBlack.isVisible = image.isSelected

            Glide.with(itemView.context).load(image.photo).into(binding.ivImage)

            itemView.setOnClickListener {
                if (image.isSelected) {
                    deleteItem(image)
                    image.isSelected = false
                    notifyItemChanged(adapterPosition)
                } else {
                    clickListener(image)
                    image.isSelected = true
                    notifyItemChanged(adapterPosition)
                }
            }
        }
    }
}

