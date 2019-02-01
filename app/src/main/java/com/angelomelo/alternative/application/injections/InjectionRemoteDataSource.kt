package com.angelomelo.alternative.application.injections

import com.angelomelo.alternative.application.service.remote.event.EventRemoteDataSource
import com.angelomelo.alternative.application.service.remote.event.EventRemoteDataSourceImpl.Companion.getInstance
import com.angelomelo.alternative.application.service.remote.eventDetail.EventDetailRemoteDataSource
import com.angelomelo.alternative.application.service.remote.eventDetail.EventDetailRemoteSourceImpl.Companion.getInstance

class InjectionRemoteDataSource {

    companion object {

        fun provideEventRemoteDataSource(): EventRemoteDataSource {
            return getInstance(InjectionApiDataSource.provideEventApiDataSource())
        }

        fun provideEventDetailRemoteDataSource(): EventDetailRemoteDataSource {
            return getInstance(InjectionApiDataSource.provideEventDetailApiDataSource())
        }
    }

}