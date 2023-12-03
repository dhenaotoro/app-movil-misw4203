package com.example.app_movil_misw4203.ui.album

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.app_movil_misw4203.R
import com.example.app_movil_misw4203.model.dto.Album
import com.squareup.picasso.Picasso
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app_movil_misw4203.model.service_adapter.TrackAdapter

class AlbumDetailActivity : AppCompatActivity() {
  private lateinit var albumViewModel: AlbumViewModel
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.album_detail)

    albumViewModel = ViewModelProvider(this, AlbumViewModel.Factory(application))[AlbumViewModel::class.java]

    val album = intent.getParcelableExtra<Album>("album")

    val albumNameTextView: TextView = findViewById(R.id.album_name)
    val albumPhoneTextView: TextView = findViewById(R.id.album_description)
    val albumImageView: ImageView = findViewById(R.id.album_image)
    val albumTrackRecyclerView: RecyclerView = findViewById(R.id.album_track_recycler_view)
    val openFragmentButton: ImageView = findViewById(R.id.button_open_fragment)
    val backToAlbumListButton: ImageView = findViewById(R.id.button_back_album)

    albumNameTextView.text = album?.name
    albumPhoneTextView.text = album?.description
    album?.cover?.let {
      Picasso.get().load(it).into(albumImageView)
    }

    album?.let {
      albumViewModel.refreshAlbumsTracks(it.id)
    }

    albumTrackRecyclerView.layoutManager = LinearLayoutManager(this)
    albumViewModel.tracks.observe(this, { tracks ->
      val trackAdapter = TrackAdapter(tracks)
      albumTrackRecyclerView.adapter = trackAdapter
    })

    supportActionBar?.title = "Detalle del album"

    backToAlbumListButton.setOnClickListener {
      onBackPressed()
    }

    openFragmentButton.setOnClickListener {
      try {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        val fragment = AlbumDetailTracks()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
      } catch (e: Exception) {
        println("Error!: ${e.localizedMessage}")
      }
    }

  }
}
