package com.example.takeimageapp.images.data

data class ImageData(
    private val id: String,
    private val description: String,
    private val imageTypes: List<String>,
    private val name: String,
) {

    interface Mapper<T> {
        fun map(
            id: String,
            description: String,
            imageTypes: List<String>,
            name: String,
        ): T
    }

    fun <T> map(mapper: Mapper<T>): T = mapper.map(id, description, imageTypes, name)
}
