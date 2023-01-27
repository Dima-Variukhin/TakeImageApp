package com.example.takeimageapp.images.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer

interface ImagesCommunication : ObserveImages {

    fun showProgress(show: Int)
    fun showList(list: List<ImageUi>)

    class Base(
        private val progressCommunication: ProgressCommunication,
        private val listCommunication: ListCommunication
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
    }
}

interface ProgressCommunication : Communication.Mutable<Int> {
    class Base : Communication.Ui<Int>(), ProgressCommunication
}

interface ListCommunication : Communication.Mutable<List<ImageUi>> {
    class Base : Communication.Ui<List<ImageUi>>(), ListCommunication
}

interface ObserveImages {
    fun observeProgress(owner: LifecycleOwner, observer: Observer<Int>)
    fun observeList(owner: LifecycleOwner, observer: Observer<List<ImageUi>>)
}