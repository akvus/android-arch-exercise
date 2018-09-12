package com.waysnpaths.carspoc.domain.repository.cars

import com.waysnpaths.carspoc.data.networking.CarsNetworking
import com.waysnpaths.carspoc.data.networking.model.AvailableVehicles
import com.waysnpaths.carspoc.data.storage.CarsStorage
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class NetworkCacheCarsRepositoryTest {

    private lateinit var repository: CarsRepository

    @Mock
    private lateinit var carsNetworking: CarsNetworking

    @Mock
    private lateinit var carsStorage: CarsStorage

    @Before
    fun init() {
        repository = NetworkCacheCarsRepository(carsNetworking, carsStorage)
    }

    @Test
    fun getCars_cacheSetWhenDataRetrieved() {
        val list: List<AvailableVehicles> = listOf()
        Mockito.`when`(carsNetworking.getCars()).thenReturn(Observable.just(list))

        val testObserver = TestObserver<List<AvailableVehicles>>()
        repository.getCars().subscribe(testObserver)

        testObserver.assertComplete()
        verify(carsStorage, times(1)).storeCars(list)

        testObserver.assertValue(list)
    }

    @Test
    fun getCars_onNetworkException_getFromCache() {
        Mockito.`when`(carsNetworking.getCars()).thenReturn(Observable.error(Throwable()))

        val list: List<AvailableVehicles> = listOf()
        Mockito.`when`(carsStorage.retrieveCars()).thenReturn(Observable.just(list))

        val testObserver = TestObserver<List<AvailableVehicles>>()
        repository.getCars().subscribe(testObserver)

        testObserver.assertComplete()
        verify(carsStorage, times(1)).retrieveCars()

        testObserver.assertValue(list)
    }

    @Test
    fun `getGars() on network and cache exception`() {
        Mockito.`when`(carsNetworking.getCars()).thenReturn(Observable.error(Throwable()))

        val error = Throwable()
        Mockito.`when`(carsStorage.retrieveCars()).thenReturn(Observable.error(error))

        val testObserver = TestObserver<List<AvailableVehicles>>()
        repository.getCars().subscribe(testObserver)

        testObserver.assertError(error)
    }
}