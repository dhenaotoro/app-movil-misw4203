package com.example.app_movil_misw4203.ui.collection

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.app_movil_misw4203.model.dto.Collector
import com.example.app_movil_misw4203.model.repository.CollectorRepository

class CollectorViewModel(application: Application) :  AndroidViewModel(application) {

  private val collectorRepository = CollectorRepository(application)

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
    collectorRepository.refreshCollectors(
      functionToCall = { collectors ->
        _collector.postValue(collectors)
        _eventNetworkError.value = false
        _isNetworkErrorShown.value = false
      },
      transmitError = {
        _eventNetworkError.value = true
      }
    )

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