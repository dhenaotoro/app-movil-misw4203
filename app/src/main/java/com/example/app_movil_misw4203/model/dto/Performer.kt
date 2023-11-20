package com.example.app_movil_misw4203.model.dto

import android.os.Parcel
import android.os.Parcelable

data class Performer(
    val id: Int,
    val name: String,
    val image: String,
    val description: String,
    val creationDate: String? = "",
    val birthDate: String? = ""
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(image)
        parcel.writeString(description)
        parcel.writeString(creationDate)
        parcel.writeString(birthDate)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Performer> {
        override fun createFromParcel(parcel: Parcel): Performer {
            return Performer(parcel)
        }

        override fun newArray(size: Int): Array<Performer?> {
            return arrayOfNulls(size)
        }
    }
}
