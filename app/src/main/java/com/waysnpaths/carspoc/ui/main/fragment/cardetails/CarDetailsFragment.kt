package com.waysnpaths.carspoc.ui.main.fragment.cardetails

import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.waysnpaths.carspoc.R
import com.waysnpaths.carspoc.ui.di.DummyInjectionFramework
import com.waysnpaths.carspoc.ui.main.model.VehicleVendor
import kotlinx.android.synthetic.main.rv_cars_item.*

class CarDetailsFragment : Fragment() {

    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: CarDetailsViewModel

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        DummyInjectionFramework.inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(CarDetailsViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_car_details, container, false)

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val vehicleVendor = arguments!!.getParcelable<VehicleVendor>(vehicleArgument)!!
        vehicleVendor.vehicle.vehicle?.vehMakeModel?.name?.let {
            tvName.text = it
            // todo what if an unexpected null
        }
        vehicleVendor.vendor.name?.let {
            tvVendor.text = it
            // todo what if an unexpected null
        }
        tvPrice.text = "${vehicleVendor.vehicle.totalCharge?.estimatedTotalAmount} ${vehicleVendor.vehicle.totalCharge?.currencyCode}"

        viewModel.onInit(vehicleVendor) // should crash otherwise as this should never happened
    }

    companion object {
        private const val vehicleArgument = "vehicleArgument"

        fun newInstance(vehicleVendor: VehicleVendor): CarDetailsFragment  {
            return CarDetailsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(vehicleArgument, vehicleVendor)
                }
            }
        }
    }
}