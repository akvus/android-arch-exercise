package com.waysnpaths.carspoc.data.storage

import com.waysnpaths.carspoc.data.networking.model.AvailableVehicles
import io.reactivex.Observable

// todo here it could be db access (flow/room/other), file, preferences or whatever else...
// todo AvailableVehicles should not be used here. There should be storage specific models and mappers to keep things clean
class StubbedCarsStorage : CarsStorage {
    override fun storeCars(cars: List<AvailableVehicles>) {

    }

    override fun retrieveCars(): Observable<List<AvailableVehicles>> {
        return Observable.just(listOf())
    }

}