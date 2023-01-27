package com.example.takeimageapp.images.domain

import com.example.takeimageapp.images.data.ImagesRepository
import com.example.takeimageapp.images.data.TestData
import com.example.takeimageapp.images.presentation.ResourceProvider
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

internal class ImagesInteractorTest {

    private lateinit var imagesInteractor: ImagesInteractor
    private lateinit var repository: TestImagesRepository
    private lateinit var resourceProvider: TestResourceProvider

    @Before
    fun setUp() {
        resourceProvider = TestResourceProvider()
        repository = TestImagesRepository()
        imagesInteractor = ImagesInteractor.Base(
            repository,
            HandleDomainRequest.Base(
                HandleError.Base(resourceProvider)
            )
        )
    }

    @Test
    fun fetchImagesSuccess() = runBlocking {
        repository.changeIsErrorExpected(false)
        repository.changeExpectedList(TestData().imageDomainList)

        val expected = ImagesDomain.Success(TestData().imageDomainList)
        val actual = imagesInteractor.fetchImages("sci-fi", 0)

        assertEquals(expected, actual)
        assertEquals(1, repository.fetchImagesCalled)
    }

    @Test
    fun fetchImagesFailure() = runBlocking {
        repository.changeIsErrorExpected(true)
        resourceProvider.changeExpected("no internet connection")

        val expected = ImagesDomain.Failure("no internet connection")
        val actual = imagesInteractor.fetchImages("sci-fi", 1)

        assertEquals(expected, actual)
        assertEquals(1, repository.fetchImagesCalled)
        assertEquals(0, repository.imagesList.size)
    }

    private class TestImagesRepository : ImagesRepository {

        var fetchImagesCalled = 0
        var imagesList = emptyList<ImageDomain>()
        private var isErrorOccurred = false

        fun changeIsErrorExpected(value: Boolean) {
            isErrorOccurred = value
        }

        fun changeExpectedList(value: List<ImageDomain>) {
            imagesList = value
        }

        override suspend fun fetchImages(query: String, page: Int): List<ImageDomain> {
            fetchImagesCalled++
            if (isErrorOccurred) {
                throw NoInternetConnectionException()
            }
            return imagesList
        }
    }

    private class TestResourceProvider : ResourceProvider {

        private var text = ""

        fun changeExpected(string: String) {
            text = string
        }

        override fun string(id: Int) = text
    }
}