package com.example.takeimageapp.images.presentation

import com.example.takeimageapp.images.domain.ImageDomain

class ImageDomainToUiMapper : ImageDomain.Mapper<ImageUi> {
    override fun map(id: String, description: String, imagesList: List<String>, name: String) =
        ImageUi(id, description, imagesList, name)
}