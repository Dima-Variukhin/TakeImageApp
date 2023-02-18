package com.example.takeimageapp.images.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer

interface ImagesCommunication : ObserveImages {

    fun showProgress(show: Int)
    fun showList(list: List<ImageUi>)
    fun showState(uiState: UiState)

    class Base(
        private val progressCommunication: ProgressCommunication,
        private val listCommunication: ListCommunication,
        private val uiStateCommunication: UiStateCommunication
    ) : ImagesCommunication {
        override fun showProgress(show: Int) {
            progressCommunication.map(show)
        }

        override fun showList(list: List<ImageUi>) {
            listCommunication.map(list)
        }

        override fun observeProgress(owner: LifecycleOwner, observer: Observer<Int>) {
            progressCommunication.observe(owner, observer)
        }

        override fun observeList(owner: LifecycleOwner, observer: Observer<List<ImageUi>>) {
            listCommunication.observe(owner, observer)
        }

        override fun observeState(owner: LifecycleOwner, observer: Observer<UiState>) {
            uiStateCommunication.observe(owner, observer)
        }

        override fun showState(uiState: UiState) = uiStateCommunication.map(uiState)
    }
}

interface ProgressCommunication : Communication.Mutable<Int> {
    class Base : Communication.Ui<Int>(), ProgressCommunication
}

interface ListCommunication : Communication.Mutable<List<ImageUi>> {
    class Base : Communication.Ui<List<ImageUi>>(), ListCommunication
}

interface UiStateCommunication : Communication.Mutable<UiState> {
    class Base : Communication.Ui<UiState>(), UiStateCommunication
}

interface ObserveImages {
    fun observeProgress(owner: LifecycleOwner, observer: Observer<Int>)
    fun observeList(owner: LifecycleOwner, observer: Observer<List<ImageUi>>)
    fun observeState(owner: LifecycleOwner, observer: Observer<UiState>)
}