package com.example.takeimageapp.images.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.takeimageapp.R
import com.example.takeimageapp.databinding.ImageItemLayoutBinding

class ImagesAdapter(private val imageLoader: ImageLoader) :
    RecyclerView.Adapter<ImagesAdapter.ImagesViewHolder>(), Mapper.Unit<List<ImageUi>> {

    private val list = mutableListOf<ImageUi>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ImagesViewHolder(
        imageLoader,
        ImageItemLayoutBinding.bind(
            LayoutInflater.from(parent.context).inflate(R.layout.image_item_layout, parent, false)
        )
    )

    override fun onBindViewHolder(holder: ImagesViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    override fun map(source: List<ImageUi>) {
        val diffUtilCallback = DiffUtilCallback(list, source)
        val result = DiffUtil.calculateDiff(diffUtilCallback)
        list.clear()
        list.addAll(source)
        result.dispatchUpdatesTo(this)
    }

    inner class ImagesViewHolder(
        imageLoader: ImageLoader,
        binding: ImageItemLayoutBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private val imageItem = binding.itemImageView
        private val nameTextView = binding.itemNameTextView
        private val nameItemDescription = binding.itemDescriptionTextView
        val mapper = ImageUi.ListItemUi(imageItem, nameTextView, nameItemDescription, imageLoader)

        fun bind(imageUi: ImageUi) {
            imageUi.map(mapper)
        }
    }
}