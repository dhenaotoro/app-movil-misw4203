package com.example.app_movil_misw4203.model.repository

import android.app.Application
import com.android.volley.VolleyError
import com.example.app_movil_misw4203.model.dto.Collector
import com.example.app_movil_misw4203.model.service_adapter.CollectorServiceAdapter

class CollectorRepository (val application: Application){
    suspend fun refreshCollectors() = CollectorServiceAdapter.getInstance(application).getCollectors()
}