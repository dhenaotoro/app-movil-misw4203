package com.example.app_movil_misw4203.model.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("collector")
data class Collector (
    @PrimaryKey val id: Int,
    val name: String,
    val telephone: String,
    val email: String
)