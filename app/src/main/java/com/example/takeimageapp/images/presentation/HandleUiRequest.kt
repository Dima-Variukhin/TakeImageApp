package com.example.takeimageapp.images.presentation

import android.view.View
import com.example.takeimageapp.images.domain.ImagesDomain
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

interface HandleUiRequest {

    fun handle(
        coroutineScope: CoroutineScope,
        block: suspend () -> ImagesDomain
    )

    class Base(
        private val dispatchers: DispatchersList,
        private val communication: ImagesCommunication,
        private val imagesDomainMapper: ImagesDomain.Mapper<Unit>
    ) : HandleUiRequest {
        override fun handle(
            coroutineScope: CoroutineScope,
            block: suspend () -> ImagesDomain
        ) {
            communication.showProgress(View.VISIBLE)
            coroutineScope.launch(dispatchers.io()) {
                val imagesDomain = block.invoke()
                withContext(dispatchers.ui()) {
                    communication.showProgress(View.GONE)
                    imagesDomain.map(imagesDomainMapper)
                }
            }
        }
    }
}