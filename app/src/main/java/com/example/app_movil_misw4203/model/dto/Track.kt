package com.example.app_movil_misw4203.model.dto

import android.os.Parcel
import android.os.Parcelable

data class Track (
  val id: Int = 0,
  val name: String,
  val duration: String
) : Parcelable {

  constructor(parcel: Parcel) : this(
    parcel.readInt(),
    parcel.readString() ?: "",
    parcel.readString() ?: ""
  )

  override fun writeToParcel(parcel: Parcel, flags: Int) {
    parcel.writeInt(id)
    parcel.writeString(name)
    parcel.writeString(duration)
  }

  override fun describeContents(): Int {
    return 0
  }

  companion object CREATOR : Parcelable.Creator<Track> {
    override fun createFromParcel(parcel: Parcel): Track {
      println("Leyendo informacion principal de objecto Track")
      println(parcel.readInt())
      println(parcel.readString())
      return Track(parcel)
    }
    override fun newArray(size: Int): Array<Track?> {
      return arrayOfNulls(size)
    }
  }
}
