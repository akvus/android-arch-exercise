package com.waysnpaths.carspoc.ui

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

class MyViewModelFactory(private val resolveViewModel: (instance: Any) -> ViewModel) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return resolveViewModel(modelClass) as T
    }
}