package com.waysnpaths.carspoc.data.networking.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class TotalCharge() : Parcelable {
    @SerializedName("@RateTotalAmount")
    val rateTotalAmount: Double? = null

    @SerializedName("@EstimatedTotalAmount")
    val estimatedTotalAmount: Double? = null

    @SerializedName("@CurrencyCode")
    val currencyCode: String? = null

    constructor(parcel: Parcel) : this() {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TotalCharge> {
        override fun createFromParcel(parcel: Parcel): TotalCharge {
            return TotalCharge(parcel)
        }

        override fun newArray(size: Int): Array<TotalCharge?> {
            return arrayOfNulls(size)
        }
    }
}