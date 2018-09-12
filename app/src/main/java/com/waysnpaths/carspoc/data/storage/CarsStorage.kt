package com.waysnpaths.carspoc.data.storage

import com.waysnpaths.carspoc.data.networking.model.AvailableVehicles
import io.reactivex.Observable

interface CarsStorage {
    fun storeCars(cars: List<AvailableVehicles>)
    fun retrieveCars(): Observable<List<AvailableVehicles>>
}