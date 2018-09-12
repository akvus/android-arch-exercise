package com.waysnpaths.carspoc.ui.main.model

import com.waysnpaths.carspoc.data.networking.model.VehVendorAvails

class VehicleVendorMapper() {
    fun map(vehVendorAvails: List<VehVendorAvails>): List<VehicleVendor> {
        val list = mutableListOf<VehicleVendor>()
        vehVendorAvails.forEach { vehVendorAvails1 ->
            vehVendorAvails1.vehAvails?.forEach { vehAvails ->
                if (vehVendorAvails1.vendor != null) {
                    list.add(VehicleVendor(vehVendorAvails1.vendor, vehAvails))
                }
            }
        }
        return list
    }
}