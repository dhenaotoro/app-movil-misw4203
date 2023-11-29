package com.example.app_movil_misw4203.model.repository

import android.app.Application
import com.example.app_movil_misw4203.model.service_adapter.AlbumServiceAdapter
import com.example.app_movil_misw4203.model.dto.Album

class AlbumRepository (val application: Application){
  suspend fun refreshAlbums() : MutableList<Album> = AlbumServiceAdapter.getInstance(application).getAlbums()

  suspend fun createAlbum(album: Album) : Album = AlbumServiceAdapter.getInstance(application).postAlbum(album)
}