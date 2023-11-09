package com.example.app_movil_misw4203.ui.collection

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
import com.example.app_movil_misw4203.databinding.FragmentCollectionBinding
import com.example.app_movil_misw4203.model.dto.Collector

class CollectorFragment : Fragment() {

  private var _binding: FragmentCollectionBinding? = null

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

    val collectionRecyclerView: RecyclerView = binding.collectionRecyclerView

    collectorViewModel.collectors.observe(viewLifecycleOwner) { collectors ->
      val customAdapter = CustomAdapter(collectors)
      collectionRecyclerView.layoutManager = LinearLayoutManager(this.context)
      collectionRecyclerView.adapter = customAdapter
    }
    return root
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}

class CustomAdapter(private val dataSet: List<Collector>) :
  RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

  class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val collectorTextView: TextView
    val performerTextView: TextView
    val coverImage: ImageView

    init {
      collectorTextView = view.findViewById(R.id.collector)
      performerTextView = view.findViewById(R.id.performer)
      coverImage = view.findViewById(R.id.cover)
    }
  }

  override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
    // Create a new view, which defines the UI of the list item
    val view = LayoutInflater.from(viewGroup.context)
      .inflate(R.layout.collector_row, viewGroup, false)

    return ViewHolder(view)
  }

  override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
    viewHolder.collectorTextView.text = dataSet[position].name
    viewHolder.performerTextView.text = dataSet[position].performers.let {
      when (it.isEmpty()) {
        true -> "Colección"
        else -> it.first().name
      }
    }
  }

  override fun getItemCount() = dataSet.size
}