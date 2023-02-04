package com.example.takeimageapp.images.presentation

import com.example.takeimageapp.images.domain.ImageDomain

class ImageDomainToUiMapper : ImageDomain.Mapper<ImageUi> {
    override fun map(id: String, description: String, imageTypes: List<String>, name: String) =
        ImageUi(id, description, imageTypes, name)
}