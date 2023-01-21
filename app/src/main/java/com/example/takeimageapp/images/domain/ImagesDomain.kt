package com.example.takeimageapp.images.domain

sealed class ImagesDomain {

    interface Mapper<T> {
        fun map(list: List<ImageDomain>): T
        fun map(errorMessage: String): T
    }

    abstract fun <T> map(mapper: Mapper<T>): T

    data class Success(private val list: List<ImageDomain> = emptyList()) : ImagesDomain() {
        override fun <T> map(mapper: Mapper<T>) = mapper.map(list)
    }

    data class Failure(private val errorMessage: String) : ImagesDomain() {
        override fun <T> map(mapper: Mapper<T>) = mapper.map(errorMessage)
    }
}