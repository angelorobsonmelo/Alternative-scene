package com.angelomelo.alternative.application.service

interface RemoteDataSourceCallback<R> {

    fun onSuccess(response: R)
    fun onError(message: String)
}