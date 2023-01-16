package com.example.takeimageapp.images.domain

import com.example.takeimageapp.R
import com.example.takeimageapp.images.presentation.ResourceProvider

interface HandleError<T> {

    fun handle(e: Exception): T

    class Base(private val resourceProvider: ResourceProvider) : HandleError<String> {
        override fun handle(e: Exception) = resourceProvider.string(
            when (e) {
                is NoInternetConnectionException -> R.string.no_connection_string
                else -> R.string.service_unavailable
            }
        )
    }
}