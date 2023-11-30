package com.example.app_movil_misw4203.model.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.app_movil_misw4203.model.database.entity.Album

@Dao
interface AlbumDao {
    @Query("SELECT * FROM album")
    fun getAlbums():List<Album>
}