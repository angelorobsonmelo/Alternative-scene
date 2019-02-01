package com.angelomelo.alternative.application.usecases.remote.event

import androidx.annotation.NonNull
import com.angelomelo.alternative.application.UseCase.LoadUseCaseCallback
import com.angelomelo.alternative.application.domain.Event
import com.angelomelo.alternative.application.domain.filter.EventFilter
import com.angelomelo.alternative.application.service.RemoteDataSourceCallback
import com.angelomelo.alternative.application.service.remote.commom.ContentObjects
import com.angelomelo.alternative.application.service.remote.commom.ResponseBase
import com.angelomelo.alternative.application.service.remote.commom.ResponseListBase
import com.angelomelo.alternative.application.service.remote.event.EventRemoteDataSource

class GetEvents(private val mEventRemoteDataSource: EventRemoteDataSource) {

    fun getEvents(@NonNull filter: EventFilter, @NonNull page: Int?, useCaseCallback: LoadUseCaseCallback<ResponseListBase<Event>>) {
        mEventRemoteDataSource.getEvents(filter, page, object : RemoteDataSourceCallback<ResponseListBase<Event>> {

            override fun onSuccess(response: ResponseListBase<Event>) {
                useCaseCallback.onLoaded(response)
            }


            override fun onError(message: String) {
                useCaseCallback.onFailed(message)
            }

        } )
    }
}