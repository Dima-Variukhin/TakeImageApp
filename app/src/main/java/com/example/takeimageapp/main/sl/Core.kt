package com.example.takeimageapp.main.sl

import android.content.Context
import com.example.takeimageapp.images.data.cloud.CloudModule
import com.example.takeimageapp.images.presentation.DispatchersList
import com.example.takeimageapp.images.presentation.ResourceProvider

interface Core : CloudModule, ResourceProvider {

    fun provideDispatchers(): DispatchersList

    class Base(context: Context) : Core {

        private val provideResources: ResourceProvider = ResourceProvider.Base(context)

        private val dispatchersList by lazy {
            DispatchersList.Base()
        }

        private val cloudModule by lazy {
            CloudModule.Base()
        }

        override fun <T> service(clazz: Class<T>) = cloudModule.service(clazz)

        override fun string(id: Int) = provideResources.string(id)

        override fun provideDispatchers() = dispatchersList
    }
}