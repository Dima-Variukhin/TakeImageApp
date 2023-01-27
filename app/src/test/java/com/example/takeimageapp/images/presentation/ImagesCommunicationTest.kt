package com.example.takeimageapp.images.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer

class ImagesCommunicationTest : ImagesCommunication {

    val progressList = mutableListOf<Int>()
    val imagesList = mutableListOf<ImageUi>()
    var showListCalled = 0
    override fun showProgress(show: Int) {
        progressList.add(show)
    }

    override fun showList(list: List<ImageUi>) {
        showListCalled++
        imagesList.addAll(list)
    }

    override fun observeProgress(owner: LifecycleOwner, observer: Observer<Int>) = Unit

    override fun observeList(owner: LifecycleOwner, observer: Observer<List<ImageUi>>) = Unit
}
