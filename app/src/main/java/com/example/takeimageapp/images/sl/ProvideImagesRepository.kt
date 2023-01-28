package com.example.takeimageapp.images.sl

import com.example.takeimageapp.images.data.HandleDataRequest
import com.example.takeimageapp.images.data.HandleDomainError
import com.example.takeimageapp.images.data.ImageDataToDomain
import com.example.takeimageapp.images.data.ImagesRepository
import com.example.takeimageapp.images.data.cloud.ImageCloudToData
import com.example.takeimageapp.images.data.cloud.ImagesCloudDataSource
import com.example.takeimageapp.images.data.cloud.ImagesService
import com.example.takeimageapp.main.sl.Core

interface ProvideImagesRepository {

    fun provideImagesRepository(): ImagesRepository

    class Base(private val core: Core) : ProvideImagesRepository {
        override fun provideImagesRepository() = ImagesRepository.Base(
            ImagesCloudDataSource.Base(
                core.service(ImagesService::class.java), ImageCloudToData.Base()
            ), HandleDataRequest.Base(ImageDataToDomain(), HandleDomainError())
        )
    }
}