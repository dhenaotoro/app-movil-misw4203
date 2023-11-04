package com.example.app_movil_misw4203.model.repository

import android.app.Application
import com.android.volley.VolleyError
import com.example.app_movil_misw4203.model.dto.Collector
import com.example.app_movil_misw4203.model.service_adapter.CollectorServiceAdapter

class CollectorRepository (val application: Application){
    fun refreshCollectors(
        functionToCall: (List<Collector>)->Unit,
        transmitError: (VolleyError)->Unit
    ) = CollectorServiceAdapter.getInstance(application).getCollectors(
        onComplete = { collectors : List<Collector> ->
            //Save all collectors in a local storage
            functionToCall(collectors)
        },
        onError = transmitError
    )
}