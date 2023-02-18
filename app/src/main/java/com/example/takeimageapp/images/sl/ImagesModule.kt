package com.example.takeimageapp.images.sl

import com.example.takeimageapp.images.domain.HandleDomainRequest
import com.example.takeimageapp.images.domain.HandleError
import com.example.takeimageapp.images.domain.ImagesInteractor
import com.example.takeimageapp.images.presentation.HandleUiRequest
import com.example.takeimageapp.images.presentation.ImageDomainToUiMapper
import com.example.takeimageapp.images.presentation.ImagesCommunication
import com.example.takeimageapp.images.presentation.ImagesDomainToUiMapper
import com.example.takeimageapp.images.presentation.ImagesViewModel
import com.example.takeimageapp.images.presentation.ListCommunication
import com.example.takeimageapp.images.presentation.ProgressCommunication
import com.example.takeimageapp.images.presentation.UiStateCommunication
import com.example.takeimageapp.main.sl.Core
import com.example.takeimageapp.main.sl.Module

class ImagesModule(
    private val core: Core,
    private val provideRepository: ProvideImagesRepository
) : Module<ImagesViewModel.Base> {

    override fun viewModel(): ImagesViewModel.Base {
        val repository = provideRepository.provideImagesRepository()

        val interactor =
            ImagesInteractor.Base(repository, HandleDomainRequest.Base(HandleError.Base(core)))

        val imagesCommunication = ImagesCommunication.Base(
            ProgressCommunication.Base(),
            ListCommunication.Base(),
            UiStateCommunication.Base()
        )

        return ImagesViewModel.Base(
            interactor,
            HandleUiRequest.Base(
                core.provideDispatchers(),
                imagesCommunication,
                ImagesDomainToUiMapper(imagesCommunication, ImageDomainToUiMapper())
            ), imagesCommunication, core
        )
    }
}