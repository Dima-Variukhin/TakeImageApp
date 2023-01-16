package com.example.takeimageapp.images.domain

abstract class DomainException : IllegalStateException()

class NoInternetConnectionException : DomainException()
class ServiceUnavailableException : DomainException()
