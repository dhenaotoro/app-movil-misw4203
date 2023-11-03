package com.example.app_movil_misw4203.model.repository

import android.app.Application
import com.android.volley.VolleyError
import com.example.app_movil_misw4203.model.service_adapter.AlbumServiceAdapter
import com.example.app_movil_misw4203.model.dto.Album

class AlbumRepository (val application: Application){
  fun refreshAlbums(
    functionToCall: (List<Album>)->Unit,
    transmitError: (VolleyError)->Unit
  ) = AlbumServiceAdapter.getInstance(application).getAlbums(
    onComplete = { albums : List<Album> ->
      //Save all albums in a local storage
      functionToCall(albums)
    },
    onError = transmitError
  )
}