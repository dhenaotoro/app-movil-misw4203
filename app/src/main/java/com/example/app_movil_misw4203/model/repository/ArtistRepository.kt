package com.example.app_movil_misw4203.model.repository

import android.app.Application
import com.android.volley.VolleyError
import com.example.app_movil_misw4203.model.service_adapter.ArtistServiceAdapter
import com.example.app_movil_misw4203.model.dto.Artist

class ArtistRepository (val application: Application){
  fun refreshArtists(
    functionToCall: (List<Artist>)->Unit,
    transmitError: (VolleyError)->Unit
  ) = ArtistServiceAdapter.getInstance(application).getArtists(
    onComplete = { artists : List<Artist> ->
      functionToCall(artists)
    },
    onError = transmitError
  )
}