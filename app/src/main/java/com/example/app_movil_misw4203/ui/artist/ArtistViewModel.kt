package com.example.app_movil_misw4203.ui.artist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ArtistViewModel : ViewModel() {

  private val _text = MutableLiveData<String>().apply {
    value = "This is gallery Fragment"
  }
  val text: LiveData<String> = _text
}