package com.example.app_movil_misw4203.model.repository

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.example.app_movil_misw4203.model.database.dao.AlbumDao
import com.example.app_movil_misw4203.model.service_adapter.AlbumServiceAdapter
import com.example.app_movil_misw4203.model.dto.Album
import com.example.app_movil_misw4203.model.dto.Track

class AlbumRepository (
  val application: Application,
  private val albumDao: AlbumDao
){
  suspend fun refreshAlbums() : List<Album> {
    val cached = albumDao.getAlbums()
    return when(cached.isNullOrEmpty()) {
      true -> {
        val cm = application.baseContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if( cm.activeNetworkInfo?.type != ConnectivityManager.TYPE_WIFI && cm.activeNetworkInfo?.type != ConnectivityManager.TYPE_MOBILE){
          emptyList()
        } else AlbumServiceAdapter.getInstance(application).getAlbums()
      }
      false -> cached.map { album ->
        Album(
          id = album.id,
          name = album.name,
          cover = album.cover,
          releaseDate = album.releaseDate,
          description = album.description,
          genre = album.genre,
          recordLabel = album.recordLabel
        )
      }
    }
  }

  suspend fun createAlbum(album: Album) : Album = AlbumServiceAdapter.getInstance(application).postAlbum(album)

  suspend fun addTrack(idTrack: Int, track: Track) = AlbumServiceAdapter.getInstance(application).postTrack(idTrack, track)
}