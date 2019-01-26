package com.angelomelo.alternative.application.injections

import br.com.stant.stant_android_occurrences.services.ApiDataSource
import com.angelomelo.alternative.application.service.remote.event.EventApiDataSource

class InjectionApiDataSource {

    companion object {

        fun provideEventApiDataSource(): EventApiDataSource {
            return ApiDataSource.createService(EventApiDataSource::class.java)
        }

    }

}