package com.example.takeimageapp.images.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.takeimageapp.R
import com.example.takeimageapp.images.domain.ImagesInteractor

interface ImagesViewModel : ObserveImages {

    fun fetchImages(query: String, page: Int)

    fun clearError()

    class Base(
        private val interactor: ImagesInteractor,
        private val handleUiRequest: HandleUiRequest,
        private val imagesCommunication: ImagesCommunication,
        private val resourceProvider: ResourceProvider
    ) : ViewModel(), ImagesViewModel {

        override fun fetchImages(query: String, page: Int) {
            if (query.isBlank() && query.isEmpty())
                imagesCommunication.showState(UiState.ShowError(resourceProvider.string(R.string.text_input_is_empty)))
            else
                handleUiRequest.handle(viewModelScope) {
                    interactor.fetchImages(query, page)
                }
        }

        override fun observeProgress(owner: LifecycleOwner, observer: Observer<Int>) {
            imagesCommunication.observeProgress(owner, observer)
        }

        override fun observeList(owner: LifecycleOwner, observer: Observer<List<ImageUi>>) {
            imagesCommunication.observeList(owner, observer)
        }

        override fun observeState(owner: LifecycleOwner, observer: Observer<UiState>) {
            imagesCommunication.observeState(owner, observer)
        }

        override fun clearError() = imagesCommunication.showState(UiState.ClearError())
    }
}