package com.example.app_movil_misw4203.ui.artist

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
import com.example.app_movil_misw4203.databinding.FragmentArtistBinding
import com.example.app_movil_misw4203.model.dto.Artist
import android.content.Intent
import com.squareup.picasso.Picasso

class ArtistFragment : Fragment() {

  private var _binding: FragmentArtistBinding? = null

  private val binding get() = _binding!!

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    val artistViewModel =
      ViewModelProvider(this)[ArtistViewModel::class.java]

    _binding = FragmentArtistBinding.inflate(inflater, container, false)
    val root: View = binding.root

    val artistRecyclerView: RecyclerView = binding.artistRecyclerView

    artistViewModel.artists.observe(viewLifecycleOwner) { artists ->
      val customAdapter = CustomAdapter(artists)
      artistRecyclerView.layoutManager = LinearLayoutManager(this.context)
      artistRecyclerView.adapter = customAdapter
    }
    return root
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}

class CustomAdapter(private val dataSet: List<Artist>) :
  RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

  class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val artistTextView: TextView
    val coverImage: ImageView

    init {
      artistTextView = view.findViewById(R.id.artist)
      coverImage = view.findViewById(R.id.cover)
    }
  }

  override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
    val view = LayoutInflater.from(viewGroup.context)
      .inflate(R.layout.artist_row, viewGroup, false)
    return ViewHolder(view)
  }

  override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
    viewHolder.artistTextView.text = dataSet[position].name
    Picasso.get().load(dataSet[position].image).into(viewHolder.coverImage)
    viewHolder.itemView.setOnClickListener {
      val artist = dataSet[position]
      val intent = Intent(viewHolder.itemView.context, ArtistDetailActivity::class.java)
      intent.putExtra("artist", artist)
      viewHolder.itemView.context.startActivity(intent)
    }
  }
  override fun getItemCount() = dataSet.size

}

// ACTO 3