package com.example.takeimageapp.images.data.cloud

import com.example.takeimageapp.images.data.ImageData

interface ImagesCloudDataSource {

    suspend fun fetchImages(query: String, page: Int): List<ImageData>

    class Base(
        private val service: ImagesService,
        private val imageCloudToData: ImageCloudToData
    ) : ImagesCloudDataSource {
        override suspend fun fetchImages(query: String, page: Int): List<ImageData> {
            val cloudData = service.searchPhotos(query, page)
            return cloudData.result.map {
                it.accept(imageCloudToData)
                imageCloudToData.makeData()
            }
        }
    }
}