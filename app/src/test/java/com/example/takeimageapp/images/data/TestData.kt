package com.example.takeimageapp.images.data

import com.example.takeimageapp.images.domain.ImageDomain

class TestData {
    val imageDataList: List<ImageData> = listOf(
        ImageData("1", "description1", "uri1", "name1"),
        ImageData("2", "description2", "uri2", "name2"),
        ImageData("3", "description3", "uri3", "name3")
    )

    val imageDomainList: List<ImageDomain> = listOf(
        ImageDomain("1", "description1", "uri1", "name1"),
        ImageDomain("2", "description2", "uri2", "name2"),
        ImageDomain("3", "description3", "uri3", "name3")
    )
}