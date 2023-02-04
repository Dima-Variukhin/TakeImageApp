package com.example.takeimageapp.images.data.cloud

import com.google.gson.annotations.SerializedName

data class ImagesResponse(
    @SerializedName("results")
    val result: List<ImageCloud>
)

data class ImageCloud(
    @SerializedName("id")
    private val id: String,
    @SerializedName("description")
    private val description: String?,
    @SerializedName("urls")
    private val urls: ImageUrlsCloud,
    @SerializedName("user")
    private val user: ImageUserCloud,
) : ReceiveMapper {
    override fun accept(mapper: ImageCloudToData) {
        mapper.saveId(id)
        mapper.saveDescription(description ?: "")
        urls.accept(mapper)
        user.accept(mapper)
    }
}

data class ImageUrlsCloud(
    @SerializedName("raw")
    private val raw: String,
    @SerializedName("full")
    private val full: String,
    @SerializedName("regular")
    private val regular: String,
    @SerializedName("small")
    private val small: String,
    @SerializedName("thumb")
    private val thumb: String,
) : ReceiveMapper {
    override fun accept(mapper: ImageCloudToData) {
        mapper.saveImageTypes(listOf(raw, full, regular, small))
    }
}

data class ImageUserCloud(
    @SerializedName("name")
    private val name: String?,
    @SerializedName("username")
    private val username: String
) : ReceiveMapper {
    override fun accept(mapper: ImageCloudToData) {
        mapper.saveName(name ?: "")
    }
}
