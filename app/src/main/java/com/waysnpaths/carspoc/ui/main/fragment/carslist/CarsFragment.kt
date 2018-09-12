package com.waysnpaths.carspoc.ui.main.fragment.carslist

import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.waysnpaths.carspoc.R
import com.waysnpaths.carspoc.extentions.plus
import com.waysnpaths.carspoc.ui.di.DummyInjectionFramework
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_cars.*
import timber.log.Timber

class CarsFragment : Fragment() {

    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: CarsViewModel

    private var carsAdapter: CarsAdapter? = null

    private var disposables = CompositeDisposable()

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        DummyInjectionFramework.inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(CarsViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_cars, container, false)

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvCars.layoutManager = LinearLayoutManager(activity)
        carsAdapter = CarsAdapter()
        rvCars.adapter = carsAdapter
        disposables += carsAdapter!!.cardClickListener.subscribe({
            viewModel.onVehicleSelected(it)
        }, Timber::e)

        viewModel.getCarsData().observe(this, Observer { data ->
            data?.let {
                carsAdapter?.setList(it)
            }
        })
        viewModel.getVehRentalCore().observe(this, Observer { vehRentalCore ->
            tvLegend.text = "${getString(R.string.pickup)}: ${vehRentalCore?.pickUpLocation?.name}, " +
                    "${getString(R.string._return)}: ${vehRentalCore?.returnLocation?.name}"
        })
        viewModel.onInit()
    }

    override fun onDestroy() {
        disposables.clear()
        super.onDestroy()
    }

    companion object {
        fun newInstance() = CarsFragment()
    }
}