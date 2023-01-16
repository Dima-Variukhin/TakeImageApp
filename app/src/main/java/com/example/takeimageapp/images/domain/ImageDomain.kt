package com.example.takeimageapp.images.domain

data class ImageDomain(
    private val id: String,
    private val description: String,
    private val full: String,
    private val name: String,
) {

    interface Mapper<T> {
        fun map(
            id: String,
            description: String,
            full: String,
            name: String,
        ): T
    }

    fun <T> map(mapper: Mapper<T>): T = mapper.map(id, description, full, name)
}