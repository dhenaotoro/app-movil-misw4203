package com.example.app_movil_misw4203.model.repository

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.example.app_movil_misw4203.model.database.dao.CollectorDao
import com.example.app_movil_misw4203.model.dto.Collector
import com.example.app_movil_misw4203.model.service_adapter.CollectorServiceAdapter

class CollectorRepository (
    val application: Application,
    private val collectorDao: CollectorDao
){
    suspend fun refreshCollectors(): List<Collector> {
        val cached = collectorDao.getCollectors()
        return when(cached.isNullOrEmpty()) {
            true -> {
                val cm = application.baseContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                if( cm.activeNetworkInfo?.type != ConnectivityManager.TYPE_WIFI && cm.activeNetworkInfo?.type != ConnectivityManager.TYPE_MOBILE){
                    emptyList()
                } else CollectorServiceAdapter.getInstance(application).getCollectors()
            }
            false -> cached.map { collector ->
                Collector(
                    id = collector.id,
                    name = collector.name,
                    telephone = collector.telephone,
                    email = collector.email
                )
            }
        }
    }
}