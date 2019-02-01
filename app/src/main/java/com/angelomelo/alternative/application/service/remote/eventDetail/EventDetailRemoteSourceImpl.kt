package com.angelomelo.alternative.application.service.remote.eventDetail

import androidx.annotation.NonNull
import com.angelomelo.alternative.application.domain.Event
import com.angelomelo.alternative.application.service.RemoteDataSourceCallback
import com.angelomelo.alternative.application.service.remote.commom.ResponseBase
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class EventDetailRemoteSourceImpl(private val mEventDetailApiDataSource: EventDetailApiDataSource): EventDetailRemoteDataSource {

    companion object {

        @Volatile
        private var INSTANCE: EventDetailRemoteSourceImpl? = null

        fun getInstance(@NonNull mEventDetailApiDataSource: EventDetailApiDataSource): EventDetailRemoteSourceImpl =
                INSTANCE ?: synchronized(this) {
                    INSTANCE ?: EventDetailRemoteSourceImpl(mEventDetailApiDataSource).also { INSTANCE = it }
                }
    }

    override fun findOne(id: Long, callback: RemoteDataSourceCallback<ResponseBase<Event>>) {
        mEventDetailApiDataSource.findOne(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result -> callback.onSuccess(result) },
                { error -> callback.onError(error.localizedMessage)}
            )
        }

    }
