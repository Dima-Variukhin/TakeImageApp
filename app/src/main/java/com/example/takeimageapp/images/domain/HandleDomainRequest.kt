package com.example.takeimageapp.images.domain

interface HandleDomainRequest {

    suspend fun handle(query: String, page: Int, block: suspend () -> List<ImageDomain>): ImagesDomain

    class Base(
        private val handleError: HandleError<String>,
    ) : HandleDomainRequest {
        override suspend fun handle(query: String, page: Int, block: suspend () -> List<ImageDomain>) = try {
            val data = block.invoke()
            ImagesDomain.Success(data)
        } catch (e: Exception) {
            ImagesDomain.Failure(handleError.handle(e))
        }
    }
}