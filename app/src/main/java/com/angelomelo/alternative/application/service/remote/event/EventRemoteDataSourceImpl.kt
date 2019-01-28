package com.angelomelo.alternative.application.service.remote.event

import android.support.annotation.NonNull
import com.angelomelo.alternative.application.domain.Event
import com.angelomelo.alternative.application.domain.filter.EventFilter
import com.angelomelo.alternative.application.service.RemoteDataSourceCallback
import com.angelomelo.alternative.application.service.remote.commom.ContentObjects
import com.angelomelo.alternative.application.service.remote.commom.ResponseBase
import com.angelomelo.alternative.application.service.remote.commom.ResponseListBase
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class EventRemoteDataSourceImpl(private val mEventApiDataSource: EventApiDataSource) : EventRemoteDataSource {

    companion object {

        @Volatile
        private var INSTANCE: EventRemoteDataSourceImpl? = null

        fun getInstance(@NonNull mEventApiDataSource: EventApiDataSource): EventRemoteDataSourceImpl =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: EventRemoteDataSourceImpl(mEventApiDataSource).also { INSTANCE = it }
            }
    }

    override fun getEvents(filter: EventFilter, page: Int?, callback: RemoteDataSourceCallback<ResponseListBase<Event>>) {
        mEventApiDataSource.getEvent(filter, page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response ->
                    callback.onSuccess(response)
                },
                { throwable ->
                    callback.onError(throwable.localizedMessage)
                }
            )
    }

}