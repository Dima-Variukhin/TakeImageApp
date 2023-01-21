package com.example.takeimageapp.images.data

import com.example.takeimageapp.images.data.cloud.ImagesCloudDataSource
import com.example.takeimageapp.images.domain.NoInternetConnectionException
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import java.net.UnknownHostException

class ImagesRepositoryTest {

    private lateinit var testImagesCloudDataSource: TestImagesCloudDataSource
    private lateinit var repository: ImagesRepository

    @Before
    fun setUp() {
        testImagesCloudDataSource = TestImagesCloudDataSource()
        repository = ImagesRepository.Base(
            testImagesCloudDataSource,
            HandleDataRequest.Base(
                ImageDataToDomain(),
                HandleDomainError()
            )
        )
    }

    @Test
    fun fetchImageDataPageOne() = runBlocking {
        testImagesCloudDataSource.makeExpectedPage(1)
        testImagesCloudDataSource.changeConnection(true)
        testImagesCloudDataSource.makeExpected(TestData().imageDataList)
        val expected = TestData().imageDomainList
        val actual = repository.fetchImages("sci-fi", 1)

        assertEquals(expected, actual)
        assertEquals(1, testImagesCloudDataSource.fetchImagesCalled)
        assertEquals(1, testImagesCloudDataSource.fetchImagesPageOneCalled)
        assertEquals(0, testImagesCloudDataSource.fetchImagesPageSixCalled)
        assertEquals(3, testImagesCloudDataSource.imageDataListPageOne.size)
        assertEquals(0, testImagesCloudDataSource.imageDataListPageSix.size)
    }

    @Test
    fun fetchImageDataPageSix() = runBlocking {
        testImagesCloudDataSource.makeExpectedPage(6)
        testImagesCloudDataSource.changeConnection(true)
        testImagesCloudDataSource.makeExpected(TestData().imageDataList)
        val expected = TestData().imageDomainList
        val actual = repository.fetchImages("sci-fi", 6)

        assertEquals(expected, actual)
        assertEquals(1, testImagesCloudDataSource.fetchImagesCalled)
        assertEquals(0, testImagesCloudDataSource.fetchImagesPageOneCalled)
        assertEquals(1, testImagesCloudDataSource.fetchImagesPageSixCalled)
        assertEquals(0, testImagesCloudDataSource.imageDataListPageOne.size)
        assertEquals(3, testImagesCloudDataSource.imageDataListPageSix.size)
    }

    @Test(expected = NoInternetConnectionException::class)
    fun fetchImageDataNoConnection() = runBlocking {
        testImagesCloudDataSource.makeExpectedPage(2)
        testImagesCloudDataSource.changeConnection(false)
        testImagesCloudDataSource.makeExpected(TestData().imageDataList)
        val expected = TestData().imageDomainList
        val actual = repository.fetchImages("sci-fi", 2)

        assertEquals(expected, actual)
        assertEquals(1, testImagesCloudDataSource.fetchImagesCalled)
        assertEquals(0, testImagesCloudDataSource.fetchImagesPageOneCalled)
        assertEquals(0, testImagesCloudDataSource.fetchImagesPageSixCalled)
        assertEquals(0, testImagesCloudDataSource.imageDataListPageOne.size)
        assertEquals(0, testImagesCloudDataSource.imageDataListPageSix.size)
    }

    class TestImagesCloudDataSource : ImagesCloudDataSource {
        var fetchImagesCalled = 0
        var fetchImagesPageOneCalled = 0
        var fetchImagesPageSixCalled = 0
        var imageDataListPageOne = emptyList<ImageData>()
        var imageDataListPageSix = emptyList<ImageData>()
        private var isConnectionAvailable = false
        private var selectedPage = 0

        fun changeConnection(value: Boolean) {
            isConnectionAvailable = value
        }

        fun makeExpected(value: List<ImageData>) {
            if (selectedPage < 5) {
                imageDataListPageOne = value
            } else {
                imageDataListPageSix = value
            }
        }

        fun makeExpectedPage(value: Int) {
            selectedPage = value
        }

        override suspend fun fetchImages(query: String, page: Int): List<ImageData> {
            fetchImagesCalled++
            return if (isConnectionAvailable) {
                if (page < 5) {
                    fetchImagesPageOneCalled++
                    imageDataListPageOne
                } else {
                    fetchImagesPageSixCalled++
                    imageDataListPageSix
                }
            } else {
                throw UnknownHostException()
            }
        }
    }
}