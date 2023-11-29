package com.example.app_movil_misw4203.ui.album

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.app_movil_misw4203.databinding.NewAlbumFormBinding
import com.example.app_movil_misw4203.model.dto.Album
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class NewAlbumActivity : AppCompatActivity() {
    private lateinit var binding: NewAlbumFormBinding
    private val genres = arrayOf("Classical", "Salsa", "Rock", "Folk")
    private val recordLabels = arrayOf("Sony Music", "EMI", "Discos Fuentes", "Elektra", "Fania Records")

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        val albumViewModel =
            ViewModelProvider(this)[AlbumViewModel::class.java]

        super.onCreate(savedInstanceState)
        setContentView(com.example.app_movil_misw4203.R.layout.new_album_form)

        binding = NewAlbumFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dropdown: Spinner = binding.genre
        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
            this,
            android.R.layout.simple_spinner_item,
            genres
        )
        var genreValue: String? = "Classical"
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        dropdown.adapter = adapter
        dropdown.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                genreValue = parent?.getItemAtPosition(position)?.toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                println("Click en no select")
            }
        }

        val dropdown_rl: Spinner = binding.recordLabel
        val adapter_rl: ArrayAdapter<String> = ArrayAdapter<String>(
            this,
            android.R.layout.simple_spinner_item,
            recordLabels
        )
        var recordLabel: String? = "Sony Music"
        adapter_rl.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        dropdown_rl.adapter = adapter_rl
        dropdown_rl.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                recordLabel = parent?.getItemAtPosition(position)?.toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                println("Click en no select")
            }
        }

        binding.createButton.setOnClickListener {
            val writtenDate = binding.groupDateEditText.text.toString()
            albumViewModel.createAlbum(
                Album(
                    name = binding.groupNameEditText.text.toString(),
                    cover = binding.groupCoverEditText.text.toString(),
                    releaseDate = when(writtenDate.matches(Regex("\\d{2}\\/\\d{2}\\/\\d{4}"))) {
                        true -> LocalDate.parse(writtenDate, DateTimeFormatter.ofPattern("dd/MM/yyyy")).format(DateTimeFormatter.ISO_DATE)
                        else -> LocalDate.now().format(DateTimeFormatter.ISO_DATE)
                    },
                    description = binding.groupDescriptionEditText.text.toString(),
                    genre = genreValue ?: "Classical",
                    recordLabel = recordLabel ?: "Sony Music"
                )
            )
        }

        val backToHome: ImageView = findViewById(com.example.app_movil_misw4203.R.id.button_back_create_album)
        backToHome.setOnClickListener {
            onBackPressed()
        }

        albumViewModel.album.observe(this) { album ->
            println("Se creo el album $album correctamente")
            Toast.makeText(this, "Se creó el album correctamente", Toast.LENGTH_LONG).show()
        }
        supportActionBar?.title = "Crear un album"
    }
}