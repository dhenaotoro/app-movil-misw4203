package com.example.app_movil_misw4203.model.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("album")
data class Album(
    @PrimaryKey val id: Int,
    val name: String,
    val cover: String,
    val releaseDate: String,
    val description: String,
    val genre:String,
    val recordLabel:String
)