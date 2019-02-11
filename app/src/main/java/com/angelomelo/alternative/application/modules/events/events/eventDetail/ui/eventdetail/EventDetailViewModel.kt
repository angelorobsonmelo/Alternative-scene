package com.angelomelo.alternative.application.modules.events.events.eventDetail.ui.eventdetail

import com.angelomelo.alternative.application.domain.Event
import com.angelomelo.alternative.application.injections.InjectionApiDataSource
import com.angelomelo.alternative.application.modules.events.events.ui.event.viewholder.BaseViewModel
import com.angelomelo.alternative.application.service.remote.commom.ResponseBase
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class EventDetailViewModel : BaseViewModel<ResponseBase<Event>>() {

    private val mEventDatailApiDataSource = InjectionApiDataSource.provideEventDetailApiDataSource()

    fun findOne(id: Long) {
        mEventDatailApiDataSource.findOne(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result -> success.value = result },
                { error -> this.error.value = error.localizedMessage  }
            )
    }

}
