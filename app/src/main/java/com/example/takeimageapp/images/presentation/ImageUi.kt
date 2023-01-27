package com.example.takeimageapp.images.presentation

data class ImageUi(
    private val id: String,
    private val description: String,
    private val imagesList: List<String>,
    private val name: String,
)