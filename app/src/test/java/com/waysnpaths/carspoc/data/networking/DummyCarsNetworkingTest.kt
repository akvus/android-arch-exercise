package com.waysnpaths.carspoc.data.networking

import com.google.gson.Gson
import com.waysnpaths.carspoc.data.networking.model.AvailableVehicles
import io.reactivex.observers.TestObserver
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class DummyCarsNetworkingTest {

    private lateinit var serviceCars: DummyCarsNetworking

    @Before
    fun init() {
        serviceCars = DummyCarsNetworking(Gson(), "http://www.cartrawler.com/ctabe/")
    }

    @Test
    fun getCars() {
        val testObserver = TestObserver<List<AvailableVehicles>>()
        serviceCars.getCars().subscribe(testObserver)
        val values = testObserver.values()
        assertThat(values, `is`(notNullValue()))
    }
}