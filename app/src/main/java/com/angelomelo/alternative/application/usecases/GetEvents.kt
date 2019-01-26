package com.angelomelo.alternative.application.usecases

import android.support.annotation.NonNull
import com.angelomelo.alternative.application.UseCase.LoadUseCaseCallback
import com.angelomelo.alternative.application.domain.Event
import com.angelomelo.alternative.application.domain.filter.EventFilter
import com.angelomelo.alternative.application.service.RemoteDataSourceCallback
import com.angelomelo.alternative.application.service.remote.commom.ContentObjects
import com.angelomelo.alternative.application.service.remote.commom.ResponseBase
import com.angelomelo.alternative.application.service.remote.event.EventRemoteDataSource

class GetEvents(private val mEventRemoteDataSource: EventRemoteDataSource) {

    fun getEvents(@NonNull filter: EventFilter, @NonNull page: Int?, useCaseCallback: LoadUseCaseCallback<ResponseBase<ContentObjects<Event>>>) {
        mEventRemoteDataSource.getEvents(filter, page, object : RemoteDataSourceCallback<ResponseBase<ContentObjects<Event>>> {

            override fun onSuccess(response: ResponseBase<ContentObjects<Event>>) {
                useCaseCallback.onLoaded(response)
            }


            override fun onError(message: String) {
                useCaseCallback.onFailed(message)
            }

        } )
    }
}