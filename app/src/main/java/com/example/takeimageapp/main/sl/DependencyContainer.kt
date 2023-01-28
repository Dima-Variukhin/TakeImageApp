package com.example.takeimageapp.main.sl

import androidx.lifecycle.ViewModel
import com.example.takeimageapp.images.presentation.ImagesViewModel
import com.example.takeimageapp.images.sl.ImagesModule
import com.example.takeimageapp.images.sl.ProvideImagesRepository

interface DependencyContainer {

    fun <T : ViewModel> module(clazz: Class<T>): Module<*>

    class Error : DependencyContainer {
        override fun <T : ViewModel> module(clazz: Class<T>): Module<*> =
            throw IllegalStateException("no module found for $clazz")
    }

    class Base(
        private val core: Core,
        private val dependencyContainer: DependencyContainer = Error()
    ) : DependencyContainer, ProvideImagesRepository {

        private val repository by lazy {
            ProvideImagesRepository.Base(core).provideImagesRepository()
        }

        override fun provideImagesRepository() = repository

        override fun <T : ViewModel> module(clazz: Class<T>) = when (clazz) {
            ImagesViewModel.Base::class.java -> ImagesModule(core, this)
            else -> {
                dependencyContainer.module(clazz)
            }
        }
    }
}