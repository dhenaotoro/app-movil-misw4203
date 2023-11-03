package com.example.app_movil_misw4203.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.app_movil_misw4203.databinding.FragmentAlbumBinding
import com.example.app_movil_misw4203.ui.album.AlbumViewModel

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

    val textView: TextView = binding.albums
    albumViewModel.albums.observe(viewLifecycleOwner) { albums ->
      textView.text = albums.toString()
    }
    return root
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}