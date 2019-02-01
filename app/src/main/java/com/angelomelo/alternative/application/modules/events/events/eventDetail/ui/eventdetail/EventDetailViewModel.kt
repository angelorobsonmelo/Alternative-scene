package com.angelomelo.alternative.application.modules.events.events.eventDetail.ui.eventdetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.angelomelo.alternative.application.UseCase
import com.angelomelo.alternative.application.domain.Event
import com.angelomelo.alternative.application.injections.InjectionUseCase.Companion.provideGetEventDetail
import com.angelomelo.alternative.application.service.remote.commom.ResponseBase

class EventDetailViewModel : ViewModel() {

    private val mGetEventDetail = provideGetEventDetail()

    var eventsResponse = MutableLiveData<ResponseBase<Event>>()
    var messageEmpty = MutableLiveData<String>()
    var error = MutableLiveData<String>()

    fun findOne(id: Long) {
        mGetEventDetail.findOne(id, object : UseCase.LoadUseCaseCallback<ResponseBase<Event>> {

            override fun onLoaded(response: ResponseBase<Event>) {
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
