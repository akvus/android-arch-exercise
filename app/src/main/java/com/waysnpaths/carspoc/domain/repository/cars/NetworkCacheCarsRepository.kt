package com.waysnpaths.carspoc.domain.repository.cars

import com.waysnpaths.carspoc.data.networking.CarsNetworking
import com.waysnpaths.carspoc.data.networking.model.AvailableVehicles
import com.waysnpaths.carspoc.data.storage.CarsStorage
import io.reactivex.Observable
import timber.log.Timber

// todo it's not clean arch, it's just MVVM + repository
class NetworkCacheCarsRepository(private val networking: CarsNetworking, private val storage: CarsStorage) : CarsRepository {
    override fun getCars(): Observable<List<AvailableVehicles>> {
        return networking.getCars()
                .doOnNext { response -> storage.storeCars(response) }
                .onErrorResumeNext { t: Throwable ->
                    Timber.e(t)
                    storage.retrieveCars()
                }

    }
}
