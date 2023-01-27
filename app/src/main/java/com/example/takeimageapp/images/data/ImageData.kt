package com.example.takeimageapp.images.data

data class ImageData(
    private val id: String,
    private val description: String,
    private val imagesList: List<String>,
    private val name: String,
) {

    interface Mapper<T> {
        fun map(
            id: String,
            description: String,
            imagesList: List<String>,
            name: String,
        ): T
    }

    fun <T> map(mapper: Mapper<T>): T = mapper.map(id, description, imagesList, name)
}
