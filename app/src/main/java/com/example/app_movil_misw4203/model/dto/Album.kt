package com.example.app_movil_misw4203.model.dto

import android.os.Parcel
import android.os.Parcelable

data class Album (
  val id: Int,
  val name: String,
  val cover: String,
  val releaseDate: String,
  val description: String,
  val genre:String,
  val recordLabel:String,
  val tracks: List<Track> = emptyList(),
  val performers: List<Performer> = emptyList(),
  val comments: List<String> = emptyList()
) : Parcelable {

  constructor(parcel: Parcel) : this(
    parcel.readInt(),
    parcel.readString() ?: "",
    parcel.readString() ?: "",
    parcel.readString() ?: "",
    parcel.readString() ?: "",
    parcel.readString() ?: "",
    parcel.readString() ?: "",
    parcel.createTypedArrayList(Track.CREATOR) ?: emptyList(),
    parcel.createTypedArrayList(Performer.CREATOR) ?: emptyList(),
    parcel.createStringArrayList() ?: emptyList()
  )

  override fun writeToParcel(parcel: Parcel, flags: Int) {
    parcel.writeInt(id)
    parcel.writeString(name)
    parcel.writeString(cover)
    parcel.writeString(releaseDate)
    parcel.writeString(description)
    parcel.writeString(genre)
    parcel.writeString(recordLabel)
    parcel.writeList(tracks)
    parcel.writeList(performers)
    parcel.writeStringList(comments)
  }

  override fun describeContents(): Int {
    return 0
  }

  companion object CREATOR : Parcelable.Creator<Album> {
    override fun createFromParcel(parcel: Parcel): Album {
      return Album(parcel)
    }

    override fun newArray(size: Int): Array<Album?> {
      return arrayOfNulls(size)
    }
  }
}