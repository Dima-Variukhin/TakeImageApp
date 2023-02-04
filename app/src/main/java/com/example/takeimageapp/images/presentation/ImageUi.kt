package com.example.takeimageapp.images.presentation

import android.widget.ImageView
import android.widget.TextView

data class ImageUi(
    private val id: String,
    private val description: String,
    private val imageTypes: List<String>,
    private val name: String,
) : Mapper<Boolean, ImageUi> {

    fun <T> map(mapper: Mapper<T>): T = mapper.map(id, description, imageTypes, name)

    interface Mapper<T> {
        fun map(
            id: String,
            description: String,
            imageTypes: List<String>,
            name: String,
        ): T
    }

    override fun map(source: ImageUi) = source.id == id

    class ListItemUi(
        private val imageView: ImageView,
        private val nameTextView: TextView,
        private val descriptionTextView: TextView,
        private val imageLoader: ImageLoader
    ) : Mapper<Unit> {
        override fun map(id: String, description: String, imageTypes: List<String>, name: String) {
            imageLoader.loadImage(imageView, imageTypes[1])
            nameTextView.text = name
            descriptionTextView.text = description
        }
    }
}