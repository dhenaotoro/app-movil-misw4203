package com.example.app_movil_misw4203.ui.collection

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CollectionViewModel : ViewModel() {

  private val _text = MutableLiveData<String>().apply {
    value = "This is slideshow Fragment"
  }
  val text: LiveData<String> = _text
}