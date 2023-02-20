package com.example.takeimageapp.images.presentation

import android.view.View
import com.example.takeimageapp.images.data.TestData
import com.example.takeimageapp.images.domain.ImageDomain
import com.example.takeimageapp.images.domain.ImagesDomain
import com.example.takeimageapp.images.domain.ImagesInteractor
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.Before
import org.junit.Test

class ImagesViewModelTest {

    private lateinit var interactor: TestImagesInteractor
    private lateinit var viewModel: ImagesViewModel
    private lateinit var communications: ImagesCommunicationTest
    private lateinit var resourceProvider: TestResourceProvider

    @Before
    fun setUp() {
        interactor = TestImagesInteractor()
        communications = ImagesCommunicationTest()
        resourceProvider = TestResourceProvider()
        viewModel = ImagesViewModel.Base(
            interactor,
            HandleUiRequest.Base(
                TestDispatchersList(),
                communications,
                ImagesDomainToUiMapper(communications, TestImagesDomainToUi())
            ), communications,
            resourceProvider
        )
    }

    @Test
    fun fetch_images_list_success() = runBlocking {
        interactor.changeExpectedResult(ImagesDomain.Success(TestData().imageDomainList))

        assertEquals(0, communications.imagesList.size)
        assertEquals(0, communications.showListCalled)
        assertEquals(0, communications.progressList.size)
        assertEquals(0, communications.showStateCalled)

        viewModel.fetchImages("sci-fi", 2)

        val expected = ImagesDomain.Success(TestData().imageDomainList)
        val actual = interactor.imagesDomainList[0]

        assertEquals(expected, actual)

        assertEquals(View.VISIBLE, communications.progressList[0])
        assertEquals(2, communications.progressList.size)
        assertEquals(3, communications.imagesList.size)
        assertEquals(1, communications.showListCalled)
        assertEquals(1, communications.showStateCalled)
        assertEquals(true, communications.stateList[0] is UiState.Success)

        assertEquals(View.GONE, communications.progressList[1])
        assertEquals(2, communications.progressList.size)
        assertEquals(1, communications.showStateCalled)
    }

    @Test
    fun fetch_empty_query_image() {
        resourceProvider.changeExpected("Text input is empty!")

        assertEquals(0, communications.imagesList.size)
        assertEquals(0, communications.showListCalled)
        assertEquals(0, communications.progressList.size)
        assertEquals(0, communications.showStateCalled)

        viewModel.fetchImages("", 1)

        val expected = 0
        val actual = interactor.imagesDomainList.size

        assertEquals(expected, actual)

        assertEquals(0, communications.imagesList.size)
        assertEquals(0, communications.showListCalled)
        assertEquals(0, communications.progressList.size)
        assertEquals(1, communications.showStateCalled)
        assertEquals(true, communications.stateList[0] is UiState.ShowError)
    }

    @Test
    fun clearError() {
        viewModel.clearError()

        assertEquals(1, communications.showStateCalled)
        assertEquals(true, communications.stateList[0] is UiState.ClearError)
    }

    private class TestImagesInteractor : ImagesInteractor {

        var result: ImagesDomain = ImagesDomain.Success()
        val imagesDomainList = mutableListOf<ImagesDomain>()

        fun changeExpectedResult(value: ImagesDomain) {
            result = value
        }

        override suspend fun fetchImages(query: String, page: Int): ImagesDomain {
            imagesDomainList.add(result)
            return result
        }
    }

    private class TestDispatchersList(
        private val dispatcher: CoroutineDispatcher = TestCoroutineDispatcher()
    ) : DispatchersList {

        override fun io(): CoroutineDispatcher = dispatcher
        override fun ui(): CoroutineDispatcher = dispatcher
    }

    private class TestImagesDomainToUi : ImageDomain.Mapper<ImageUi> {
        override fun map(
            id: String,
            description: String,
            imageTypes: List<String>,
            name: String
        ) = ImageUi(id, description, imageTypes, name)
    }

    private class TestResourceProvider : ResourceProvider {

        private var text = ""

        fun changeExpected(string: String) {
            text = string
        }

        override fun string(id: Int) = text
    }
}