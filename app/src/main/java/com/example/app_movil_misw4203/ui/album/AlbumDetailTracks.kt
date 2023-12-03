package com.example.app_movil_misw4203.ui.album

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.app_movil_misw4203.R
import com.example.app_movil_misw4203.model.dto.Track
import com.example.app_movil_misw4203.model.service_adapter.AlbumTrackAdapter

class AlbumDetailTracks : Fragment() {

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    val trackList: List<Track> = listOf(
      Track(1, "Untitled", "5:05"),
      Track(2, "Untitled 2", "5:05"),
      Track(3, "Untitled 3", "7:05"),
      Track(3, "Decisiones", "5:05"),
      Track(3, "Desapariciones", "6:29")
    )
    val view = inflater.inflate(R.layout.album_detail_tracks, container, false)

    val recyclerView: RecyclerView = view.findViewById(R.id.track_lists)
    recyclerView.layoutManager = LinearLayoutManager(context)
    recyclerView.adapter = AlbumTrackAdapter(trackList)

    return view
  }
}

