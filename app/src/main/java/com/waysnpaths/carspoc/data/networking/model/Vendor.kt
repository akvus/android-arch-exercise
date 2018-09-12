package com.waysnpaths.carspoc.data.networking.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class Vendor() : Parcelable {
    @SerializedName("@Code")
    val code: Int? = null

    @SerializedName("@Name")
    val name: String? = null

    constructor(parcel: Parcel) : this() {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Vendor> {
        override fun createFromParcel(parcel: Parcel): Vendor {
            return Vendor(parcel)
        }

        override fun newArray(size: Int): Array<Vendor?> {
            return arrayOfNulls(size)
        }
    }
}