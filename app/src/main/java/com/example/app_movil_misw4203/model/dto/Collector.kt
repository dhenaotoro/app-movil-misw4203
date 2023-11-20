package com.example.app_movil_misw4203.model.dto

import android.os.Parcel
import android.os.Parcelable

data class Collector (
  val id: Int,
  val name:String,
  val telephone:String,
  val email:String,
  val favoritePerformers: Set<Performer> = emptySet(),
) : Parcelable {

  constructor(parcel: Parcel) : this(
    parcel.readInt(),
    parcel.readString() ?: "",
    parcel.readString() ?: "",
    parcel.readString() ?: "",
    emptySet()
  )

  override fun writeToParcel(parcel: Parcel, flags: Int) {
    parcel.writeInt(id)
    parcel.writeString(name)
    parcel.writeString(telephone)
    parcel.writeString(email)
    parcel.writeList(favoritePerformers.toList())
  }

  override fun describeContents(): Int {
    return 0
  }

  companion object CREATOR : Parcelable.Creator<Collector> {
    override fun createFromParcel(parcel: Parcel): Collector {
      return Collector(parcel)
    }

    override fun newArray(size: Int): Array<Collector?> {
      return arrayOfNulls(size)
    }
  }
}
