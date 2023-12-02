package com.example.app_movil_misw4203.model.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.app_movil_misw4203.model.database.entity.Collector

@Dao
interface CollectorDao {
    @Query("SELECT * FROM collector")
    fun getCollectors(): List<Collector>
}