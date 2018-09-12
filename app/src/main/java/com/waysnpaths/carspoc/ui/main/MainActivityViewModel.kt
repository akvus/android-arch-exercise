package com.waysnpaths.carspoc.ui.main

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.waysnpaths.carspoc.extentions.plus
import com.waysnpaths.carspoc.ui.MyViewModel
import com.waysnpaths.carspoc.ui.navigation.CarsListNavigationState
import com.waysnpaths.carspoc.ui.navigation.NavigationEvent
import com.waysnpaths.carspoc.ui.navigation.NavigationState
import io.reactivex.android.schedulers.AndroidSchedulers
import timber.log.Timber

class MainActivityViewModel constructor(
        private val navigationEvent: NavigationEvent
) : MyViewModel() {
    private val stateLiveData: MutableLiveData<NavigationState> by lazy { MutableLiveData<NavigationState>() }

    fun init() {
        disposables += navigationEvent.subject.observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    stateLiveData.value = it
                }, Timber::e)

        navigationEvent.goTo(CarsListNavigationState)
    }

    fun getState(): LiveData<NavigationState> {
        return stateLiveData
    }

}