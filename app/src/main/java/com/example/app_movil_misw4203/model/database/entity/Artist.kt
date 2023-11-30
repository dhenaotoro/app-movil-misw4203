package com.example.app_movil_misw4203.model.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("artist")
data class Artist (
    @PrimaryKey val id: Int,
    val name: String,
    val image: String,
    val description: String,
    val birthDate: String
)