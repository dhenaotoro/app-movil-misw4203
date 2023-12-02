package com.example.app_movil_misw4203.ui.album

import AppMain
import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.app_movil_misw4203.R
import com.example.app_movil_misw4203.databinding.FragmentAlbumBinding
import com.example.app_movil_misw4203.model.dto.Album
import com.squareup.picasso.Picasso

class AlbumFragment : Fragment() {

  private var _binding: FragmentAlbumBinding? = null

  private val binding get() = _binding!!

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    val albumViewModel =
      ViewModelProvider(this, AlbumViewModel.Factory(activity?.application ?: Application()))[AlbumViewModel::class.java]
    _binding = FragmentAlbumBinding.inflate(inflater, container, false)
    val root: View = binding.root
    val albumRecyclerView: RecyclerView = binding.albumRecyclerView
    albumViewModel.albums.observe(viewLifecycleOwner) { albums ->
      val customAdapter = CustomAdapter(albums)
      albumRecyclerView.layoutManager = LinearLayoutManager(this.context)
      albumRecyclerView.adapter = customAdapter
    }
    return root
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}

class CustomAdapter(private val dataSet: List<Album>) :
  RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

  class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val albumTextView: TextView
    val performerTextView: TextView
    val coverImage: ImageView

    init {
      albumTextView = view.findViewById(R.id.album)
      performerTextView = view.findViewById(R.id.performer)
      coverImage = view.findViewById(R.id.cover)
    }
  }

  override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
    val view = LayoutInflater.from(viewGroup.context)
      .inflate(R.layout.album_row, viewGroup, false)
    return ViewHolder(view)
  }

  override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
    viewHolder.albumTextView.text = dataSet[position].name
    viewHolder.performerTextView.text = dataSet[position].performers.let {
      when (it.isEmpty()) {
        true -> "Album"
        else -> it.first().name
      }
    }
    viewHolder.itemView.setOnClickListener {
      val album = dataSet[position]
      val intent = Intent(viewHolder.itemView.context, AlbumDetailActivity::class.java)
      intent.putExtra("album", album)
      viewHolder.itemView.context.startActivity(intent)
    }
    val cover = dataSet[position].cover
    Picasso.get().load(cover).into(viewHolder.coverImage)
  }

  override fun getItemCount() = dataSet.size

}