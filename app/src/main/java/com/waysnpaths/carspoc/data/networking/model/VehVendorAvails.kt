package com.waysnpaths.carspoc.data.networking.model

import com.google.gson.annotations.SerializedName

class VehVendorAvails {
    @SerializedName("Vendor")
    val vendor: Vendor? = null

    @SerializedName("VehAvails")
    val vehAvails: List<VehAvails>? = null
}