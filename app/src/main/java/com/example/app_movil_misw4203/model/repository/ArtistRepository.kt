package com.example.app_movil_misw4203.model.repository

import android.app.Application
import com.android.volley.VolleyError
import com.example.app_movil_misw4203.model.dto.Album
import com.example.app_movil_misw4203.model.service_adapter.ArtistServiceAdapter
import com.example.app_movil_misw4203.model.dto.Artist

class ArtistRepository (val application: Application){
  suspend fun refreshArtists() : MutableList<Artist> = ArtistServiceAdapter.getInstance(application).getArtists()
}