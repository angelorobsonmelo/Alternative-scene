package com.angelomelo.alternative.application.injections

import com.angelomelo.alternative.application.service.remote.event.EventRemoteDataSource
import com.angelomelo.alternative.application.service.remote.event.EventRemoteDataSourceImpl

class InjectionRemoteDataSource {

    companion object {

        fun eventRemoteDataSource(): EventRemoteDataSource {
            return EventRemoteDataSourceImpl.getInstance(InjectionApiDataSource.provideEventApiDataSource())
        }
    }

}