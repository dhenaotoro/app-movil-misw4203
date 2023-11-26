package com.example.app_movil_misw4203.ui.album

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.app_movil_misw4203.R
import com.example.app_movil_misw4203.model.dto.Album
import com.squareup.picasso.Picasso
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app_movil_misw4203.model.dto.Track
import com.example.app_movil_misw4203.model.service_adapter.TrackAdapter

class AlbumDetailActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.album_detail)

    val album = intent.getParcelableExtra<Album>("album")

    println("Name: ${album?.name}")
    println("Description: ${album?.description}")
    println("Tracks Off 1: ${album?.tracks}")
    println("Tracks Off 2: ${album?.tracks?.toList()}")
    println("Tracks Off 3: ${ArrayList(album?.tracks)}")
    println("Tracks Off 4: ${album?.tracks}")

    val albumNameTextView: TextView = findViewById(R.id.album_name)
    val albumPhoneTextView: TextView = findViewById(R.id.album_description)
    val albumImageView: ImageView = findViewById(R.id.album_image)
    val albumTrackRecyclerView: RecyclerView = findViewById(R.id.album_track_recycler_view)

    albumNameTextView.text = album?.name
    albumPhoneTextView.text = album?.description
    album?.cover?.let {
      Picasso.get().load(it).into(albumImageView)
    }

    albumTrackRecyclerView.layoutManager = LinearLayoutManager(this)
    val trackAdapter = TrackAdapter(album?.tracks ?: emptyList())
    albumTrackRecyclerView.adapter = trackAdapter

    supportActionBar?.title = "Detalle del album"

    val backToAlbumListButton: ImageView = findViewById(R.id.button_back_album)
    backToAlbumListButton.setOnClickListener {
      onBackPressed()
    }
  }
}
