package com.example.app_movil_misw4203.model.dto

import android.os.Parcel
import android.os.Parcelable

data class Artist (
  val id: Int,
  val name: String,
  val image: String,
  val description: String,
  val birthDate: String,
  val albums: Set<Album> = emptySet()
) : Parcelable {

  constructor(parcel: Parcel) : this(
    parcel.readInt(),
    parcel.readString() ?: "",
    parcel.readString() ?: "",
    parcel.readString() ?: "",
    parcel.readString() ?: "",
    emptySet()
  )

  override fun writeToParcel(parcel: Parcel, flags: Int) {
    parcel.writeInt(id)
    parcel.writeString(name)
    parcel.writeString(image)
    parcel.writeString(description)
    parcel.writeString(birthDate)
  }

  override fun describeContents(): Int {
    return 0
  }

  companion object CREATOR : Parcelable.Creator<Artist> {
    override fun createFromParcel(parcel: Parcel): Artist {
      return Artist(parcel)
    }

    override fun newArray(size: Int): Array<Artist?> {
      return arrayOfNulls(size)
    }
  }
}