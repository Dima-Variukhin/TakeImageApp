package com.example.takeimageapp.images.domain

import com.example.takeimageapp.images.data.ImagesRepository

interface ImagesInteractor {

    suspend fun fetchImages(query: String, page: Int): ImagesDomain

    class Base(
        private val repository: ImagesRepository,
        private val handleDomainRequest: HandleDomainRequest
    ) : ImagesInteractor {
        override suspend fun fetchImages(query: String, page: Int) =
            handleDomainRequest.handle(query, page) {
                repository.fetchImages(query, page)
            }
    }
}