package com.waysnpaths.carspoc.ui.main.model

import android.os.Parcel
import android.os.Parcelable
import com.waysnpaths.carspoc.data.networking.model.VehAvails
import com.waysnpaths.carspoc.data.networking.model.Vendor

data class VehicleVendor(
        val vendor: Vendor,
        val vehicle: VehAvails
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readParcelable(Vendor::class.java.classLoader),
            parcel.readParcelable(VehAvails::class.java.classLoader)) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(vendor, flags)
        parcel.writeParcelable(vehicle, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<VehicleVendor> {
        override fun createFromParcel(parcel: Parcel): VehicleVendor {
            return VehicleVendor(parcel)
        }

        override fun newArray(size: Int): Array<VehicleVendor?> {
            return arrayOfNulls(size)
        }
    }
}