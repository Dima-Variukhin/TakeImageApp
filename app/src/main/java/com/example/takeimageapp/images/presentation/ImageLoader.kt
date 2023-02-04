package com.example.takeimageapp.images.presentation

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

interface ImageLoader {

    fun loadImage(
        imageView: ImageView,
        image: String
    )

    class Base(private val context: Context) : ImageLoader {
        override fun loadImage(imageView: ImageView, image: String) {
            Glide.with(context)
                .load(image)
                .centerCrop()
                .placeholder(com.google.android.material.R.drawable.abc_list_selector_background_transition_holo_dark)
                .error(com.google.android.material.R.drawable.mtrl_ic_error)
                .into(imageView)
        }
    }
}