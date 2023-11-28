package com.example.app_movil_misw4203.ui.album

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.ui.AppBarConfiguration
import com.example.app_movil_misw4203.R
import com.example.app_movil_misw4203.databinding.NewAlbumFormBinding
import com.example.app_movil_misw4203.model.dto.Album

class NewAlbumActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: NewAlbumFormBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        val albumViewModel =
            ViewModelProvider(this)[AlbumViewModel::class.java]

        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_album_form)

        binding = NewAlbumFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.createButton.setOnClickListener {
            albumViewModel.createAlbum(
                Album(
                    name = binding.groupNameEditText.text.toString(),
                    cover = binding.groupCoverEditText.text.toString(),
                    releaseDate = binding.groupDateEditText.text.toString(),
                    description = binding.groupDescriptionEditText.text.toString(),
                    genre = binding.groupGenderEditText.text.toString(),
                    recordLabel = binding.groupRecordingEditText.text.toString()
                )
            )
        }

        val backToHome: ImageView = findViewById(R.id.button_back_create_album)
        backToHome.setOnClickListener {
            onBackPressed()
        }
        //setSupportActionBar(binding.appBarMain.toolbar)

        /*val collector = intent.getParcelableExtra<Collector>("collector")

        supportActionBar?.title = "Detalle de la colección"
        */
    }
}