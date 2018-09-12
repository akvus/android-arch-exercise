package com.waysnpaths.carspoc.data.networking

import com.waysnpaths.carspoc.data.networking.model.AvailableVehicles
import io.reactivex.Observable

interface CarsNetworking {
    fun getCars(): Observable<List<AvailableVehicles>>
}