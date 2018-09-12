package com.waysnpaths.carspoc.ui.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.waysnpaths.carspoc.R
import com.waysnpaths.carspoc.ui.di.DummyInjectionFramework
import com.waysnpaths.carspoc.ui.main.fragment.cardetails.CarDetailsFragment
import com.waysnpaths.carspoc.ui.main.fragment.carslist.CarsFragment
import com.waysnpaths.carspoc.ui.navigation.CarDetailsNavigationState
import com.waysnpaths.carspoc.ui.navigation.CarsListNavigationState
import com.waysnpaths.carspoc.ui.navigation.NavigationState

class MainActivity : AppCompatActivity() {

    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DummyInjectionFramework.inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainActivityViewModel::class.java)

        setContentView(R.layout.activity_main)
        viewModel.getState().observe(this, Observer { state ->
            state?.let { setState(it) }
        })
        viewModel.init()
    }

    private fun setState(state: NavigationState) {
        when (state) {
            is CarsListNavigationState -> setFragment(CarsFragment.newInstance(), state.backStack)
            is CarDetailsNavigationState -> setFragment(CarDetailsFragment.newInstance(state.vehicleVendor), state.backStack)
        }
    }

    private fun setFragment(fragment: Fragment, backStack: Boolean) {
        val transaction = supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
        if (backStack) transaction.addToBackStack(fragment.javaClass.name)
        transaction.commit()
    }
}
