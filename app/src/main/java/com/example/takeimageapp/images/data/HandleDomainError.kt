package com.example.takeimageapp.images.data

import com.example.takeimageapp.images.domain.HandleError
import com.example.takeimageapp.images.domain.NoInternetConnectionException
import com.example.takeimageapp.images.domain.ServiceUnavailableException
import java.net.UnknownHostException
import kotlin.Exception

class HandleDomainError : HandleError<Exception> {

    override fun handle(e: Exception) = when (e) {
        is UnknownHostException -> NoInternetConnectionException()
        else -> ServiceUnavailableException()
    }
}