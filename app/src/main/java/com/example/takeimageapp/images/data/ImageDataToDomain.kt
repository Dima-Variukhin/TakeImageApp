package com.example.takeimageapp.images.data

import com.example.takeimageapp.images.domain.ImageDomain

class ImageDataToDomain : ImageData.Mapper<ImageDomain> {
    override fun map(
        id: String,
        description: String,
        imagesList: List<String>,
        name: String
    ): ImageDomain {
        return ImageDomain(id, description, imagesList, name)
    }
}