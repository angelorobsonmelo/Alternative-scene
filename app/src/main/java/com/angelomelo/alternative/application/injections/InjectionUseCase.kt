package com.angelomelo.alternative.application.injections

import com.angelomelo.alternative.application.injections.InjectionRemoteDataSource.Companion.provideEventDetailRemoteDataSource
import com.angelomelo.alternative.application.injections.InjectionRemoteDataSource.Companion.provideEventRemoteDataSource
import com.angelomelo.alternative.application.usecases.remote.event.GetEventDetail
import com.angelomelo.alternative.application.usecases.remote.event.GetEvents

class InjectionUseCase {

    companion object {

        fun provideGetEvents(): GetEvents {
            return GetEvents(provideEventRemoteDataSource())
        }

        fun provideGetEventDetail(): GetEventDetail {
            return GetEventDetail(provideEventDetailRemoteDataSource())
        }
    }

}