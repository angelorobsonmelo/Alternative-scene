package com.angelomelo.alternative.application.modules.events.events.ui.event

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.angelomelo.alternative.application.domain.Event
import com.angelomelo.alternative.application.domain.filter.EventFilter
import com.angelomelo.alternative.application.injections.InjectionApiDataSource
import com.angelomelo.alternative.application.service.remote.commom.ResponseListBase
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class EventViewModel : ViewModel() {

    private val mEventApiDataSource = InjectionApiDataSource.provideEventApiDataSource()

    var eventsResponse = MutableLiveData<ResponseListBase<Event>>()
    var messageEmpty = MutableLiveData<String>()
    var error = MutableLiveData<String>()

    fun getEvents(filter: EventFilter, page: Int?) {
        mEventApiDataSource.getEvent(filter, page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnCompleted {
                val events = eventsResponse.value?.data?.content

                if (page == 0 && events?.isEmpty()!!) {
                    messageEmpty.value = "Empty message"
                }

            }
            .subscribe(
                { result -> eventsResponse.value = result },
                { error -> this.error.value = error.localizedMessage  }
            )
    }

}
