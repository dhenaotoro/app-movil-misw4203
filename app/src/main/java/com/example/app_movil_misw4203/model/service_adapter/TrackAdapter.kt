package com.example.app_movil_misw4203.model.service_adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.app_movil_misw4203.R
import com.example.app_movil_misw4203.model.dto.Track

class TrackAdapter(private val trackList: List<Track>) : RecyclerView.Adapter<TrackAdapter.TrackViewHolder>() {

  class TrackViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val trackName: TextView = itemView.findViewById(R.id.track_name)
    val trackDuration: TextView = itemView.findViewById(R.id.track_duration)
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder {
    val itemView = LayoutInflater.from(parent.context)
      .inflate(R.layout.album_detail_row, parent, false)
    return TrackViewHolder(itemView)
  }

  override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {
    val currentTrack = trackList[position]
    holder.trackName.text = currentTrack?.name
    holder.trackDuration.text = currentTrack?.duration
  }

  override fun getItemCount(): Int {
    return trackList.size
  }
}
