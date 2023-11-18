package com.example.app_movil_misw4203.ui.artist

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.app_movil_misw4203.R
import com.example.app_movil_misw4203.model.dto.Artist
import com.squareup.picasso.Picasso

class ArtistDetailActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.artist_detail)

    val artist = intent.getParcelableExtra<Artist>("artist")

    val artistNameTextView: TextView = findViewById(R.id.artist_name)
    val artistDescriptionTextView: TextView = findViewById(R.id.artist_description)
    val artistDateTextView: TextView = findViewById(R.id.artist_date)
    val artistAwardsTextView: TextView = findViewById(R.id.artist_awards)
    val artistImageView: ImageView = findViewById(R.id.artist_image)

    artistNameTextView.text = artist?.name
    artistDescriptionTextView.text = artist?.description
    artistDateTextView.text = artist?.birthDate.toString().split("T")[0]
    artistAwardsTextView.text = ""
    artist?.image?.let {
      Picasso.get().load(it).into(artistImageView)
    }

    supportActionBar?.title = "Detalle del artista"

    val backToArtistListButton: ImageView = findViewById(R.id.button_back_artist)
    backToArtistListButton.setOnClickListener {
      onBackPressed()
    }
  }
}
