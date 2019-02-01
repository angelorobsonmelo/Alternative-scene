package com.angelomelo.alternative.application.usecases.remote.event

import androidx.annotation.NonNull
import com.angelomelo.alternative.application.UseCase
import com.angelomelo.alternative.application.domain.Event
import com.angelomelo.alternative.application.service.RemoteDataSourceCallback
import com.angelomelo.alternative.application.service.remote.commom.ResponseBase
import com.angelomelo.alternative.application.service.remote.eventDetail.EventDetailRemoteDataSource


class GetEventDetail(private val mEventDetailRemoteDataSource: EventDetailRemoteDataSource) {

    fun findOne(@NonNull id: Long, useCaseCallback: UseCase.LoadUseCaseCallback<ResponseBase<Event>>) {
        mEventDetailRemoteDataSource.findOne(id, object : RemoteDataSourceCallback<ResponseBase<Event>> {
            override fun onSuccess(response: ResponseBase<Event>) {
                useCaseCallback.onLoaded(response)
            }

            override fun onError(message: String) {
                useCaseCallback.onFailed(message)
            }

        } )
    }
}