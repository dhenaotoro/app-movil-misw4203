package com.example.app_movil_misw4203.ui.collection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.app_movil_misw4203.databinding.FragmentCollectionBinding

class CollectorFragment : Fragment() {

  private var _binding: FragmentCollectionBinding? = null

  // This property is only valid between onCreateView and
  // onDestroyView.
  private val binding get() = _binding!!

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    val collectorViewModel =
      ViewModelProvider(this)[CollectorViewModel::class.java]

    _binding = FragmentCollectionBinding.inflate(inflater, container, false)
    val root: View = binding.root

    val textView: TextView = binding.collectors
    collectorViewModel.collectors.observe(viewLifecycleOwner) { collectors ->
      textView.text = collectors.toString()
    }
    return root
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}