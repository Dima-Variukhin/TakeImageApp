package com.example.takeimageapp.images.data

import com.example.takeimageapp.images.domain.ImageDomain

class TestData {
    private val imagesList = listOf("uri1", "uri2", "uri3", "uri4")
    val imageDataList: List<ImageData> = listOf(
        ImageData("1", "description1", imagesList, "name1"),
        ImageData("2", "description2", imagesList, "name2"),
        ImageData("3", "description3", imagesList, "name3")
    )

    val imageDomainList: List<ImageDomain> = listOf(
        ImageDomain("1", "description1", imagesList, "name1"),
        ImageDomain("2", "description2", imagesList, "name2"),
        ImageDomain("3", "description3", imagesList, "name3")
    )
}