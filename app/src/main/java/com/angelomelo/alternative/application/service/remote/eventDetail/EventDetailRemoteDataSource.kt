package com.angelomelo.alternative.application.service.remote.eventDetail

import androidx.annotation.NonNull
import com.angelomelo.alternative.application.domain.Event
import com.angelomelo.alternative.application.service.RemoteDataSourceCallback
import com.angelomelo.alternative.application.service.remote.commom.ResponseBase

interface EventDetailRemoteDataSource {

    fun findOne(@NonNull id: Long, callback: RemoteDataSourceCallback<ResponseBase<Event>>)
}