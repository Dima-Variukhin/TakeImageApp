package com.example.takeimageapp.images.presentation

import com.example.takeimageapp.images.domain.ImageDomain
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Test

class ImagesDomainToUiMapperTest {

    //TODO add test for error handling

    private lateinit var communications: ImagesCommunicationTest
    private lateinit var imagesDomainToUiMapper: ImagesDomainToUiMapper

    @Before
    fun setUp() {
        communications = ImagesCommunicationTest()
        imagesDomainToUiMapper = ImagesDomainToUiMapper(communications, ImageDomainToUiMapper())
    }

    @Test
    fun success_with_empty_list() {
        imagesDomainToUiMapper.map(emptyList())

        assertTrue(communications.imagesList.isEmpty())
        assertEquals(0, communications.showListCalled)
    }

    @Test
    fun success_with_list() {
        imagesDomainToUiMapper.map(listOf(ImageDomain("1", "description1", "image1", "name1")))

        assertFalse(communications.imagesList.isEmpty())
        assertEquals(1, communications.showListCalled)
    }
}
