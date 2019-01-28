package com.angelomelo.alternative.application.modules.events.events.ui.event

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.angelomelo.alternative.application.UseCase
import com.angelomelo.alternative.application.domain.Event
import com.angelomelo.alternative.application.domain.filter.EventFilter
import com.angelomelo.alternative.application.injections.InjectionUseCase.Companion.provideGetEvents
import com.angelomelo.alternative.application.service.remote.commom.ResponseListBase
import com.angelomelo.alternative.application.usecases.GetEvents

class EventViewModel : ViewModel() {

    private val mGetEvents: GetEvents = provideGetEvents()

    var eventsResponse = MutableLiveData<ResponseListBase<Event>>()
    var messageEmpty = MutableLiveData<String>()
    var error = MutableLiveData<String>()

    fun getEvents(filter: EventFilter, page: Int?) {
        mGetEvents.getEvents(filter, page, object : UseCase.LoadUseCaseCallback<ResponseListBase<Event>> {

            override fun onLoaded(response: ResponseListBase<Event>) {
                eventsResponse.value = response
            }

            override fun onEmptyData() {
                messageEmpty.value = "Value is empty"
            }

            override fun onFailed(errorDescription: String) {
              error.value = errorDescription
            }

        })
    }

}
