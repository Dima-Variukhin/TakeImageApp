package com.example.takeimageapp.images.presentation

import com.example.takeimageapp.images.domain.ImageDomain
import com.example.takeimageapp.images.domain.ImagesDomain

class ImagesDomainToUiMapper(
    private val communications: ImagesCommunication,
    private val mapper: ImageDomain.Mapper<ImageUi>
) : ImagesDomain.Mapper<Unit> {
    override fun map(list: List<ImageDomain>) {
        if (list.isNotEmpty()) communications.showList(list.map { it.map(mapper) })
    }

    override fun map(errorMessage: String) {
        //TODO add error handling
    }
}