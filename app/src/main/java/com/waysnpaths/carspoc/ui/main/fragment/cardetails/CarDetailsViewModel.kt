package com.waysnpaths.carspoc.ui.main.fragment.cardetails

import android.arch.lifecycle.ViewModel
import com.waysnpaths.carspoc.ui.main.model.VehicleVendor
import com.waysnpaths.carspoc.ui.navigation.NavigationEvent

class CarDetailsViewModel constructor(
        private val navigationEvent: NavigationEvent
) : ViewModel() {
    fun onInit(vehicleVendor: VehicleVendor) {
        // todo do something with vehicleVendor
    }
}