package com.example.app_movil_misw4203.model.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.app_movil_misw4203.model.database.entity.Artist

@Dao
interface ArtistDao {
    @Query("SELECT * FROM artist")
    fun getArtists(): List<Artist>
}