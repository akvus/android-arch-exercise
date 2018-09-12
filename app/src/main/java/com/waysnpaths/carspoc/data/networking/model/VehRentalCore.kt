package com.waysnpaths.carspoc.data.networking.model

import com.google.gson.annotations.SerializedName
import java.util.*

class VehRentalCore {
    @SerializedName("@PickUpDateTime")
    val pickUpDateTime: Date? = null

    @SerializedName("@ReturnDateTime")
    val returnDateTime: Date? = null

    @SerializedName("PickUpLocation")
    val pickUpLocation : Location? = null

    @SerializedName("ReturnLocation")
    val returnLocation : Location? = null
}