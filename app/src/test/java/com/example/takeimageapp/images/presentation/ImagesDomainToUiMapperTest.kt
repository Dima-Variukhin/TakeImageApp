package com.example.takeimageapp.images.presentation

import com.example.takeimageapp.images.data.TestData
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Test

class ImagesDomainToUiMapperTest {

    private lateinit var communications: ImagesCommunicationTest
    private lateinit var imagesDomainToUiMapper: ImagesDomainToUiMapper

    @Before
    fun setUp() {
        communications = ImagesCommunicationTest()
        imagesDomainToUiMapper = ImagesDomainToUiMapper(communications, ImageDomainToUiMapper())
    }

    @Test
    fun test_error() {
        imagesDomainToUiMapper.map("some error")

        assertEquals(1, communications.stateList.size)
        assertEquals(UiState.ShowError("some error"), communications.stateList[0])
    }

    @Test
    fun success_with_empty_list() {
        imagesDomainToUiMapper.map(emptyList())

        assertTrue(communications.imagesList.isEmpty())
        assertEquals(0, communications.showListCalled)
        assertEquals(true, communications.stateList[0] is UiState.Success)
        assertEquals(1, communications.stateList.size)
    }

    @Test
    fun success_with_list() {
        imagesDomainToUiMapper.map(listOf(TestData().imageDomainList[0]))

        assertFalse(communications.imagesList.isEmpty())
        assertEquals(1, communications.showListCalled)
        assertEquals(true, communications.stateList[0] is UiState.Success)
        assertEquals(1, communications.stateList.size)
    }
}
