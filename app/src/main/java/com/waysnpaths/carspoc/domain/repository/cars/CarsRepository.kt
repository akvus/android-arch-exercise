package com.waysnpaths.carspoc.domain.repository.cars

import com.waysnpaths.carspoc.data.networking.model.AvailableVehicles
import io.reactivex.Observable

interface CarsRepository {
    fun getCars() : Observable<List<AvailableVehicles>> // todo there should be a seperate model in this layer and a mapper between gson model and this
}