package com.angelomelo.alternative.application.service.remote.event

import com.angelomelo.alternative.application.domain.Event
import com.angelomelo.alternative.application.domain.filter.EventFilter
import com.angelomelo.alternative.application.service.remote.commom.ContentObjects
import com.angelomelo.alternative.application.service.remote.commom.ResponseBase
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import rx.Observable

interface EventApiDataSource {

    @POST("v1/events/filter")
    fun getEvent(@Body filter: EventFilter, @Header("pag") page: Int?): Observable<ResponseBase<ContentObjects<Event>>>

}