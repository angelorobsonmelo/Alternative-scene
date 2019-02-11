package com.angelomelo.alternative.application.modules.events.events.ui.event.viewholder

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel<T>: ViewModel() {

    var success = MutableLiveData<T>()
    var messageEmpty = MutableLiveData<String>()
    var error = MutableLiveData<String>()
}