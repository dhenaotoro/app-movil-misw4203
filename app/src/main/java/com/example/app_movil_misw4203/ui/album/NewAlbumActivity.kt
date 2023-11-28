package com.example.app_movil_misw4203.ui.album

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import com.example.app_movil_misw4203.R
import com.example.app_movil_misw4203.databinding.ActivityMainBinding
import com.example.app_movil_misw4203.databinding.NewAlbumFormBinding
import com.example.app_movil_misw4203.model.dto.Collector

class NewAlbumActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: NewAlbumFormBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_album_form)

        binding = NewAlbumFormBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setSupportActionBar(binding.appBarMain.toolbar)


        /*val collector = intent.getParcelableExtra<Collector>("collector")

        val collectorNameTextView: TextView = findViewById(R.id.collector_name)
        val collectorPhoneTextView: TextView = findViewById(R.id.collector_phone)
        val collectorMailTextView: TextView = findViewById(R.id.collector_mail)
        val collectorPerformerTextView: TextView = findViewById(R.id.collector_performer)

        collectorNameTextView.text = collector?.name
        collectorPhoneTextView.text = collector?.telephone
        collectorMailTextView.text = collector?.email
        collector?.favoritePerformers?.let { performers ->
            if (performers.isNotEmpty()) {
                val performerList = performers.joinToString(separator = "\n") { it.name }
                collectorPerformerTextView.text = performerList
            } else {
                collectorPerformerTextView.text = "No hay artistas asociados a este coleccionista."
            }
        } ?: run {
            collectorPerformerTextView.text = "Información de coleccionista no disponible."
        }

        supportActionBar?.title = "Detalle de la colección"

        val backToCollectorListButton: ImageView = findViewById(R.id.button_back_collector)
        backToCollectorListButton.setOnClickListener {
            onBackPressed()
        }*/
    }
}