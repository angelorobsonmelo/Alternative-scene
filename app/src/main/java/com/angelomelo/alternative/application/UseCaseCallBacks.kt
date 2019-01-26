package com.angelomelo.alternative.application

interface UseCase {

    interface VoidUseCaseCallback {
        fun onSuccess()
        fun onError(errorDescription: String)
    }

    interface LoadUseCaseCallback<R> {
        fun onLoaded(response: R)
        fun onEmptyData()
        fun onFailed(errorDescription: String)
    }


}