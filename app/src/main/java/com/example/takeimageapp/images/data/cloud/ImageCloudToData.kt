package com.example.takeimageapp.images.data.cloud

import com.example.takeimageapp.images.data.ImageData

interface ImageCloudToData {

    fun saveId(id: String)
    fun saveDescription(description: String)
    fun saveImageTypes(imageTypes: List<String>)
    fun saveName(name: String)

    fun makeData(): ImageData

    class Base : ImageCloudToData {
        private var id: String = ""
        private var description: String = ""
        private var imageTypes: List<String> = emptyList()
        private var name: String = ""

        override fun saveId(id: String) {
            this.id = id
        }

        override fun saveDescription(description: String) {
            this.description = description
        }

        override fun saveImageTypes(imageTypes: List<String>) {
            this.imageTypes = imageTypes
        }

        override fun saveName(name: String) {
            this.name = name
        }

        override fun makeData() = ImageData(id, description, imageTypes, name)
    }
}