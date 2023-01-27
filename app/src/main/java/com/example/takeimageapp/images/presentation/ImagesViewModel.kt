package com.example.takeimageapp.images.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.takeimageapp.images.domain.ImagesInteractor

interface ImagesViewModel : ObserveImages {

    fun fetchImages(query: String, page: Int)

    class Base(
        private val interactor: ImagesInteractor,
        private val handleUiRequest: HandleUiRequest,
        private val imagesCommunication: ImagesCommunication
    ) : ViewModel(), ImagesViewModel {

        override fun fetchImages(query: String, page: Int) =
            handleUiRequest.handle(viewModelScope) {
                interactor.fetchImages(query, page)
            }

        override fun observeProgress(owner: LifecycleOwner, observer: Observer<Int>) {
            imagesCommunication.observeProgress(owner, observer)
        }

        override fun observeList(owner: LifecycleOwner, observer: Observer<List<ImageUi>>) {
            imagesCommunication.observeList(owner, observer)
        }
    }
}