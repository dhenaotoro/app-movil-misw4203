package com.example.app_movil_misw4203.model.repository

import android.app.Application
import com.android.volley.VolleyError
import com.example.app_movil_misw4203.model.service_adapter.AlbumServiceAdapter
import com.example.app_movil_misw4203.model.dto.Album

class AlbumRepository (val application: Application){
  suspend fun refreshAlbums() : MutableList<Album> = AlbumServiceAdapter.getInstance(application).getAlbums()
}