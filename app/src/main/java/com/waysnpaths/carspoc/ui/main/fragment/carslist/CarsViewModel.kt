package com.waysnpaths.carspoc.ui.main.fragment.carslist

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.waysnpaths.carspoc.data.networking.model.AvailableVehicles
import com.waysnpaths.carspoc.data.networking.model.VehRentalCore
import com.waysnpaths.carspoc.domain.repository.cars.CarsRepository
import com.waysnpaths.carspoc.extentions.plus
import com.waysnpaths.carspoc.ui.MyViewModel
import com.waysnpaths.carspoc.ui.main.model.VehicleVendor
import com.waysnpaths.carspoc.ui.main.model.VehicleVendorMapper
import com.waysnpaths.carspoc.ui.navigation.CarDetailsNavigationState
import com.waysnpaths.carspoc.ui.navigation.NavigationEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class CarsViewModel constructor(
        private val navigationEvent: NavigationEvent,
        private val carsRepository: CarsRepository,
        private val vehicleVendorMapper: VehicleVendorMapper
) : MyViewModel() {
    private val carsDataLiveData: MutableLiveData<List<VehicleVendor>> by lazy { MutableLiveData<List<VehicleVendor>>() }
    private val vehRentalCoreLiveData: MutableLiveData<VehRentalCore> by lazy { MutableLiveData<VehRentalCore>() }
    private var carDataCache: List<AvailableVehicles>? = null

    fun onInit() {
        disposables += carsRepository.getCars().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    if (response.isNotEmpty()) {
                        carDataCache = response
                        response[0].vehAvailRSCore?.vehVendorAvails?.let {
                            var vehicleVendors = vehicleVendorMapper.map(it)
                            vehicleVendors = vehicleVendors.sortedWith(compareBy {
                                it.vehicle.totalCharge?.estimatedTotalAmount ?: 0
                            })
                            carsDataLiveData.value = vehicleVendors
                        }

                        response[0].vehAvailRSCore?.vehRentalCore?.let {
                            vehRentalCoreLiveData.value = it
                        }
                    }
                }, Timber::e) // todo singleLiveData with toast message on error
    }

    fun onVehicleSelected(vehicleVendor: VehicleVendor) {
        navigationEvent.goTo(CarDetailsNavigationState(vehicleVendor))
    }

    fun getCarsData(): LiveData<List<VehicleVendor>> = carsDataLiveData

    fun getVehRentalCore(): LiveData<VehRentalCore> = vehRentalCoreLiveData
}