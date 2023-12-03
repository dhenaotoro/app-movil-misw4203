package com.example.app_movil_misw4203.model.service_adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.app_movil_misw4203.R
import com.example.app_movil_misw4203.model.dto.Track

class AlbumTrackAdapter(private val trackList: List<Track>) : RecyclerView.Adapter<AlbumTrackAdapter.TrackViewHolder>() {

  class TrackViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val nameTextView: TextView = itemView.findViewById(R.id.track_name)
    val durationTextView: TextView = itemView.findViewById(R.id.track_duration)
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder {
    val itemView = LayoutInflater.from(parent.context)
      .inflate(R.layout.track_item, parent, false)
    return TrackViewHolder(itemView)
  }

  override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {
    val track = trackList[position]
    holder.nameTextView.text = track.name
    holder.durationTextView.text = track.duration
  }

  override fun getItemCount(): Int {
    return trackList.size
  }
}
