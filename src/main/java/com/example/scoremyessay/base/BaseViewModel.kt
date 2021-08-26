package com.example.scoremyessay.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bumptech.glide.load.engine.Resource
import com.example.scoremyessay.data.network.BaseResponse
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel :ViewModel(){
    var eventLoading = MutableLiveData<Event<Boolean>>()
        private set
    var eventError = MutableLiveData<Event<String>>()
        private set

    fun showLoading(value: Boolean) {
        eventLoading.value = Event(value)
    }

    fun showError(ErrorString: String) {
        eventError.value = Event(ErrorString)
    }
}