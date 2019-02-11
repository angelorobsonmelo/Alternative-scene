package com.angelomelo.alternative.application.modules.events.events.ui.event

import com.angelomelo.alternative.application.domain.Event
import com.angelomelo.alternative.application.domain.filter.EventFilter
import com.angelomelo.alternative.application.injections.InjectionApiDataSource
import com.angelomelo.alternative.application.modules.events.events.ui.event.viewholder.BaseViewModel
import com.angelomelo.alternative.application.service.remote.commom.ResponseListBase
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class EventViewModel : BaseViewModel<ResponseListBase<Event>>() {

    private val mEventApiDataSource = InjectionApiDataSource.provideEventApiDataSource()

    fun getEvents(filter: EventFilter, page: Int?) {
        mEventApiDataSource.getEvent(filter, page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnCompleted {
                val events = success.value?.data?.content

                if (page == 0 && events?.isEmpty()!!) {
                    messageEmpty.value = "Empty message"
                }

            }
            .subscribe(
                { result -> success.value = result },
                { error -> this.error.value = error.localizedMessage  }
            )
    }

}
