package com.example.app_movil_misw4203.ui.artist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.app_movil_misw4203.model.dto.Artist
import com.example.app_movil_misw4203.model.repository.ArtistRepository
import com.example.app_movil_misw4203.model.service_adapter.ArtistServiceAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ArtistViewModel(application: Application) :  AndroidViewModel(application) {

  private val artistRepository = ArtistRepository(application)

  private val _artists = MutableLiveData<List<Artist>>()

  val artists: LiveData<List<Artist>>
    get() = _artists

  private var _eventNetworkError = MutableLiveData<Boolean>(false)

  val eventNetworkError: LiveData<Boolean>
    get() = _eventNetworkError

  private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)

  val isNetworkErrorShown: LiveData<Boolean>
    get() = _isNetworkErrorShown

  init {
    try {
      refreshArtistsFromNetwork()
    } catch (e: Exception) {
      println("ERROR: " + e.localizedMessage)
    }
  }

  private fun refreshArtistsFromNetwork() =
    try {
      viewModelScope.launch(Dispatchers.Default) {
        withContext(Dispatchers.IO) {
          val artists = artistRepository.refreshArtists()
          _artists.postValue(artists)
        }
        _eventNetworkError.postValue(false)
        _isNetworkErrorShown.postValue( false)
      }
    } catch (e:Exception) {
      _eventNetworkError.value = true
    }

  fun onNetworkErrorShown() {
    _isNetworkErrorShown.value = true
  }

  class Factory(val app: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
      if (modelClass.isAssignableFrom(ArtistViewModel::class.java)) {
        @Suppress("UNCHECKED_CAST")
        return ArtistViewModel(app) as T
      }
      throw IllegalArgumentException("Unable to construct view-model")
    }
  }
}