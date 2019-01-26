package com.angelomelo.alternative.application.injections

import com.angelomelo.alternative.application.injections.InjectionRemoteDataSource.Companion.eventRemoteDataSource
import com.angelomelo.alternative.application.usecases.GetEvents

class InjectionUseCase {

    companion object {

        fun provideGetEvents(): GetEvents {
            return GetEvents(eventRemoteDataSource())
        }
    }

}