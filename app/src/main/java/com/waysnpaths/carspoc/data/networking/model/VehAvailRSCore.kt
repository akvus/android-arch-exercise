package com.waysnpaths.carspoc.data.networking.model

import com.google.gson.annotations.SerializedName

class VehAvailRSCore {
    @SerializedName("VehRentalCore")
    val vehRentalCore: VehRentalCore? = null

    @SerializedName("VehVendorAvails")
    val vehVendorAvails: List<VehVendorAvails>? = null
}