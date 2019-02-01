package com.angelomelo.alternative.application.service.remote.eventDetail

import com.angelomelo.alternative.application.domain.Event
import com.angelomelo.alternative.application.service.remote.commom.ResponseBase
import retrofit2.http.GET
import retrofit2.http.Path
import rx.Observable

interface EventDetailApiDataSource {

    @GET("v1/events/{id}")
    fun findOne(@Path("id") id: Long): Observable<ResponseBase<Event>>

}