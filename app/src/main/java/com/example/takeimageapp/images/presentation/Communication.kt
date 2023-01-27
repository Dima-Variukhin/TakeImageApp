package com.example.takeimageapp.images.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

interface Communication {

    interface Observe<T> {
        fun observe(owner: LifecycleOwner, observer: Observer<T>)
    }

    interface Mutable<T> : Observe<T>, Mapper.Unit<T>

    abstract class Ui<T>(
        private val liveData: MutableLiveData<T> = MutableLiveData()
    ) : Mutable<T> {

        override fun map(source: T) {
            liveData.value = source
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<T>) {
            liveData.observe(owner, observer)
        }
    }
}