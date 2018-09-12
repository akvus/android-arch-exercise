package com.waysnpaths.carspoc.data.networking.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class Vehicle() : Parcelable {

    @SerializedName("@AirConditionInd")
    val airConditionInd: Boolean? = null

    @SerializedName("@TransmissionType")
    val transmissionType: String? = null

    @SerializedName("@FuelType")
    val fuelType: String? = null

    @SerializedName("@DriveType")
    val driveType: String? = null

    @SerializedName("@PassengerQuantity")
    val passengerQuantity: String? = null

    @SerializedName("@BaggageQuantity")
    val baggageQuantity: String? = null

    @SerializedName("@Code")
    val code: String? = null

    @SerializedName("@CodeContext")
    val codeContext: String? = null

    @SerializedName("@DoorCount")
    val doorCount: Int? = null

    @SerializedName("VehMakeModel")
    val vehMakeModel: VehMakeModel? = null

    @SerializedName("@PictureURL")
    val pictureURL: String? = null

    constructor(parcel: Parcel) : this() {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Vehicle> {
        override fun createFromParcel(parcel: Parcel): Vehicle {
            return Vehicle(parcel)
        }

        override fun newArray(size: Int): Array<Vehicle?> {
            return arrayOfNulls(size)
        }
    }
}