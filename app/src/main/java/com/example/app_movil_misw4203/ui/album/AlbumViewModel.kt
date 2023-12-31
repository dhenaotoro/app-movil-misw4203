package com.example.app_movil_misw4203.ui.album

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.app_movil_misw4203.model.database.AppDatabase
import com.example.app_movil_misw4203.model.dto.Album
import com.example.app_movil_misw4203.model.dto.Track
import com.example.app_movil_misw4203.model.repository.AlbumRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AlbumViewModel(application: Application) :  AndroidViewModel(application) {

  private val albumRepository = AlbumRepository(application, AppDatabase.getDatabase(application.applicationContext).albumDao())

  private val _albums = MutableLiveData<List<Album>>()
  private val _album = MutableLiveData<Album>()

  val albums: LiveData<List<Album>>
    get() = _albums

  private val _trackAssociated = MutableLiveData<String>()

  val trackAssociated: LiveData<String>
    get() = _trackAssociated

  val album: LiveData<Album>
    get() = _album

  private var _eventNetworkError = MutableLiveData<Boolean>(false)

  val eventNetworkError: LiveData<Boolean>
    get() = _eventNetworkError

  private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)

  val isNetworkErrorShown: LiveData<Boolean>
    get() = _isNetworkErrorShown

  private val _tracks = MutableLiveData<List<Track>>()

  val tracks: LiveData<List<Track>>
    get() = _tracks

  init {
    refreshAlbumsFromNetwork()
  }

  fun refreshAlbumsTracks(albumId: Int) {
    try {
      viewModelScope.launch(Dispatchers.Default) {
        withContext(Dispatchers.IO) {
          _tracks.postValue(albumRepository.getTracksForAlbum(albumId))
        }
        _eventNetworkError.postValue(false)
        _isNetworkErrorShown.postValue(false)
      }
    } catch (e: Exception) {
      _eventNetworkError.value = true
    }
  }

  private fun refreshAlbumsFromNetwork() =
    try {
      viewModelScope.launch(Dispatchers.Default) {
        withContext(Dispatchers.IO) {
          val albums = albumRepository.refreshAlbums()
          _albums.postValue(albums)
        }
        _eventNetworkError.postValue(false)
        _isNetworkErrorShown.postValue(false)
      }
    } catch (e: Exception) {
      _eventNetworkError.value = true
    }

  fun createAlbum(albumToCreate: Album) {
    try {
      viewModelScope.launch(Dispatchers.Default) {
        withContext(Dispatchers.IO) {
          val album = albumRepository.createAlbum(albumToCreate)
          _album.postValue(album)
        }
        _eventNetworkError.postValue(false)
        _isNetworkErrorShown.postValue(false)
      }
    } catch (e: Exception) {
      _eventNetworkError.value = true
    }
  }

  fun associateTracksWithAlbum(albumId: Int, track: Track) =
    try {
      viewModelScope.launch(Dispatchers.Default) {
        withContext(Dispatchers.IO) {
          albumRepository.addTrack(albumId, track)
          _trackAssociated.postValue("Process successfully")
        }
        _eventNetworkError.postValue(false)
        _isNetworkErrorShown.postValue(false)
      }
    } catch (e: Exception) {
      _eventNetworkError.value = true
    }
  fun onNetworkErrorShown() {
    _isNetworkErrorShown.value = true
  }

  class Factory(val app: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
      if (modelClass.isAssignableFrom(AlbumViewModel::class.java)) {
        @Suppress("UNCHECKED_CAST")
        return AlbumViewModel(app) as T
      }
      throw IllegalArgumentException("Unable to construct view-model")
    }
  }
}