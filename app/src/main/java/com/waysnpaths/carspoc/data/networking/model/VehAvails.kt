package com.waysnpaths.carspoc.data.networking.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class VehAvails() : Parcelable {
    @SerializedName("@Status")
    val status: String? = null

    @SerializedName("Vehicle")
    val vehicle: Vehicle? = null

    @SerializedName("TotalCharge")
    val totalCharge: TotalCharge? = null

    constructor(parcel: Parcel) : this() {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<VehAvails> {
        override fun createFromParcel(parcel: Parcel): VehAvails {
            return VehAvails(parcel)
        }

        override fun newArray(size: Int): Array<VehAvails?> {
            return arrayOfNulls(size)
        }
    }
}