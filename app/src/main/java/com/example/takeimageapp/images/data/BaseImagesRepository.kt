package com.example.takeimageapp.images.data

import com.example.takeimageapp.images.data.cloud.ImagesCloudDataSource
import com.example.takeimageapp.images.domain.ImageDomain

interface ImagesRepository {

    suspend fun fetchImages(query: String, page: Int): List<ImageDomain>

    class Base(
        private val imagesCloudDataSource: ImagesCloudDataSource,
        private val handleDataRequest: HandleDataRequest
    ) : ImagesRepository {

        override suspend fun fetchImages(query: String, page: Int) = handleDataRequest.handle {
            imagesCloudDataSource.fetchImages(query, page)
        }
    }
}