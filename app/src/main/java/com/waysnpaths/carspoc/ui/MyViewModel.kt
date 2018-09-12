package com.waysnpaths.carspoc.ui

import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class MyViewModel : ViewModel() {
    protected var disposables = CompositeDisposable()

    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }
}