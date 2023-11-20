package com.example.app_movil_misw4203.ui.album

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

  // This property is only valid between onCreateView and
  // onDestroyView.
  private val binding get() = _binding!!

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    val albumViewModel =
      ViewModelProvider(this)[AlbumViewModel::class.java]

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

  /**
   * Provide a reference to the type of views that you are using
   * (custom ViewHolder)
   */
  class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val albumTextView: TextView
    val performerTextView: TextView
    val coverImage: ImageView

    init {
      // Define click listener for the ViewHolder's View
      albumTextView = view.findViewById(R.id.album)
      performerTextView = view.findViewById(R.id.performer)
      coverImage = view.findViewById(R.id.cover)
    }
  }

  // Create new views (invoked by the layout manager)
  override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
    // Create a new view, which defines the UI of the list item
    val view = LayoutInflater.from(viewGroup.context)
      .inflate(R.layout.album_row, viewGroup, false)

    return ViewHolder(view)
  }

  // Replace the contents of a view (invoked by the layout manager)
  override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

    // Get element from your dataset at this position and replace the
    // contents of the view with that element
    println("Data to show in ReciclerView ${dataSet[position]}")
    viewHolder.albumTextView.text = dataSet[position].name
    viewHolder.performerTextView.text = dataSet[position].performers.let {
      when (it.isEmpty()) {
        true -> "Album"
        else -> it.first().name
      }
    }
    //A way to capture image from a server on internet and load into ImageViewComponent
    val cover = dataSet[position].cover
    Picasso.get().load(cover).into(viewHolder.coverImage)
  }

  // Return the size of your dataset (invoked by the layout manager)
  override fun getItemCount() = dataSet.size

}