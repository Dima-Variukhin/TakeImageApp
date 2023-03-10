package com.example.takeimageapp.images.presentation

import android.content.Context
import androidx.annotation.StringRes

interface ResourceProvider {

    fun string(@StringRes id: Int): String

    class Base(private val context: Context) : ResourceProvider {
        override fun string(id: Int): String = context.getString(id)
    }
}