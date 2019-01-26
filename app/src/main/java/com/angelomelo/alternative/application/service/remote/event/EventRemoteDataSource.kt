package com.angelomelo.alternative.application.service.remote.event

import android.support.annotation.NonNull
import com.angelomelo.alternative.application.domain.Event
import com.angelomelo.alternative.application.domain.filter.EventFilter
import com.angelomelo.alternative.application.service.RemoteDataSourceCallback
import com.angelomelo.alternative.application.service.remote.commom.ContentObjects
import com.angelomelo.alternative.application.service.remote.commom.ResponseBase

interface EventRemoteDataSource {

    fun getEvents(filter: EventFilter, @NonNull page: Int?, callback: RemoteDataSourceCallback<ResponseBase<ContentObjects<Event>>>)
}