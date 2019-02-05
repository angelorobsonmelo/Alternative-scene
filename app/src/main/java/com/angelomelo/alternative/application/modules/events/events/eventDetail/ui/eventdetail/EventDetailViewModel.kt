package com.angelomelo.alternative.application.modules.events.events.eventDetail.ui.eventdetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.angelomelo.alternative.application.domain.Event
import com.angelomelo.alternative.application.injections.InjectionApiDataSource
import com.angelomelo.alternative.application.service.remote.commom.ResponseBase
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class EventDetailViewModel : ViewModel() {

    private val mEventDatailApiDataSource = InjectionApiDataSource.provideEventDetailApiDataSource()

    var eventsResponse = MutableLiveData<ResponseBase<Event>>()
    var messageEmpty = MutableLiveData<String>()
    var error = MutableLiveData<String>()

    fun findOne(id: Long) {
        mEventDatailApiDataSource.findOne(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result -> eventsResponse.value = result },
                { error -> this.error.value = error.localizedMessage  }
            )
    }

}
