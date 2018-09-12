package com.waysnpaths.carspoc.ui.di

import android.arch.lifecycle.ViewModel
import com.google.gson.Gson
import com.waysnpaths.carspoc.data.networking.DummyCarsNetworking
import com.waysnpaths.carspoc.data.storage.StubbedCarsStorage
import com.waysnpaths.carspoc.domain.repository.cars.NetworkCacheCarsRepository
import com.waysnpaths.carspoc.ui.MyViewModelFactory
import com.waysnpaths.carspoc.ui.main.MainActivity
import com.waysnpaths.carspoc.ui.main.MainActivityViewModel
import com.waysnpaths.carspoc.ui.main.fragment.cardetails.CarDetailsFragment
import com.waysnpaths.carspoc.ui.main.fragment.cardetails.CarDetailsViewModel
import com.waysnpaths.carspoc.ui.main.fragment.carslist.CarsFragment
import com.waysnpaths.carspoc.ui.main.fragment.carslist.CarsViewModel
import com.waysnpaths.carspoc.ui.main.model.VehicleVendorMapper
import com.waysnpaths.carspoc.ui.navigation.NavigationEvent

// In real world we'd use some dependency injection framework i.e dagger 2 or koin
class DummyInjectionFramework private constructor() {

    val viewModelFactory by lazy { MyViewModelFactory(this::resolveViewModel) }

    private fun resolveViewModel(instance: Any): ViewModel {
        return when (instance) {
            MainActivityViewModel::class.java -> MainActivityViewModel(NavigationEvent)
            CarDetailsViewModel::class.java -> CarDetailsViewModel(NavigationEvent)
            CarsViewModel::class.java -> CarsViewModel(NavigationEvent,
                    NetworkCacheCarsRepository(DummyCarsNetworking(Gson(), "http://www.cartrawler.com/ctabe/"),
                            StubbedCarsStorage()), VehicleVendorMapper())
            else -> throw IllegalStateException("Trying to perform not defined injection.")
        }
    }

    companion object {
        private val instance by lazy { DummyInjectionFramework() }

        fun inject(activity: MainActivity) {
            activity.viewModelFactory = instance.viewModelFactory
        }

        fun inject(fragment: CarsFragment) {
            fragment.viewModelFactory = instance.viewModelFactory
        }

        fun inject(fragment: CarDetailsFragment) {
            fragment.viewModelFactory = instance.viewModelFactory
        }
    }
}