package com.example.app_movil_misw4203.ui.collection

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.app_movil_misw4203.model.database.AppDatabase
import com.example.app_movil_misw4203.model.dto.Collector
import com.example.app_movil_misw4203.model.repository.CollectorRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CollectorViewModel(application: Application) :  AndroidViewModel(application) {

  private val collectorRepository = CollectorRepository(application, AppDatabase.getDatabase(application.applicationContext).collectorDao())

  private val _collector = MutableLiveData<List<Collector>>()

  val collectors: LiveData<List<Collector>>
    get() = _collector

  private var _eventNetworkError = MutableLiveData<Boolean>(false)

  val eventNetworkError: LiveData<Boolean>
    get() = _eventNetworkError

  private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)

  val isNetworkErrorShown: LiveData<Boolean>
    get() = _isNetworkErrorShown

  init {
    refreshCollectorsFromNetwork()
  }

  private fun refreshCollectorsFromNetwork() =
    try {
      viewModelScope.launch(Dispatchers.Default) {
        withContext(Dispatchers.IO) {
          val collectors = collectorRepository.refreshCollectors()
          _collector.postValue(collectors)
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
      if (modelClass.isAssignableFrom(CollectorViewModel::class.java)) {
        @Suppress("UNCHECKED_CAST")
        return CollectorViewModel(app) as T
      }
      throw IllegalArgumentException("Unable to construct view-model")
    }
  }
}