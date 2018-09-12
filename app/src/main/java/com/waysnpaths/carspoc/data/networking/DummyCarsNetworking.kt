package com.waysnpaths.carspoc.data.networking

import android.accounts.NetworkErrorException
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.waysnpaths.carspoc.data.networking.model.AvailableVehicles
import io.reactivex.Observable
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.InterruptedIOException
import java.net.URL

// todo this could be retrofit or any other networking library, to keep it simple, it's just a basic getObservable
class DummyCarsNetworking(private val gson: Gson, private val url: String) : CarsNetworking {

    override fun getCars(): Observable<List<AvailableVehicles>> {
        return Observable.fromCallable { retrieveCars() }
    }

    private fun retrieveCars(): List<AvailableVehicles> {
        val url = URL(url + "cars.json")

        return try {
            val reader = BufferedReader(InputStreamReader(url.openStream()))
            var fullString = ""
            while (true) {
                val line = reader.readLine() ?: break
                fullString += line
            }
            reader.close()
            val type = object : TypeToken<List<AvailableVehicles>>() {}.type
            gson.fromJson<List<AvailableVehicles>>(fullString, type)
        } catch (e: InterruptedIOException) {
            e.printStackTrace()
            throw NetworkErrorException()
        }
    }
}