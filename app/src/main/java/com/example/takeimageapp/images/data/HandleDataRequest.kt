package com.example.takeimageapp.images.data

import com.example.takeimageapp.images.domain.HandleError
import com.example.takeimageapp.images.domain.ImageDomain
import java.lang.Exception

interface HandleDataRequest {

    suspend fun handle(block: suspend () -> List<ImageData>): List<ImageDomain>

    class Base(
        private val mapperToDomain: ImageData.Mapper<ImageDomain>,
        private val handleError: HandleError<Exception>
    ) : HandleDataRequest {

        override suspend fun handle(block: suspend () -> List<ImageData>): List<ImageDomain> = try {
            val result = block.invoke()
            result.map { it.map(mapperToDomain) }
        } catch (e: Exception) {
            throw handleError.handle(e)
        }
    }
}