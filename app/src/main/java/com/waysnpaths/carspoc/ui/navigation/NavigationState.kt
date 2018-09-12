package com.waysnpaths.carspoc.ui.navigation

import com.waysnpaths.carspoc.ui.main.model.VehicleVendor

sealed class NavigationState(val backStack: Boolean)

object CarsListNavigationState : NavigationState(false)
data class CarDetailsNavigationState(val vehicleVendor: VehicleVendor) : NavigationState(true)