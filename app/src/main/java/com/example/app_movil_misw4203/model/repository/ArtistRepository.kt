package com.example.app_movil_misw4203.model.repository

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.example.app_movil_misw4203.model.database.dao.ArtistDao
import com.example.app_movil_misw4203.model.service_adapter.ArtistServiceAdapter
import com.example.app_movil_misw4203.model.dto.Artist

class ArtistRepository (
  val application: Application,
  private val artistDao: ArtistDao
){
  suspend fun refreshArtists() : List<Artist> {
    val cached = artistDao.getArtists()
    return when(cached.isNullOrEmpty()) {
      true -> {
        val cm = application.baseContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if( cm.activeNetworkInfo?.type != ConnectivityManager.TYPE_WIFI && cm.activeNetworkInfo?.type != ConnectivityManager.TYPE_MOBILE){
          emptyList()
        } else ArtistServiceAdapter.getInstance(application).getArtists()
      }
      false -> cached.map { artist ->
        Artist(
          id = artist.id,
          name = artist.name,
          image = artist.image,
          description = artist.description,
          birthDate = artist.birthDate
        )
      }
    }
  }
}