package com.example.soccerapp.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.soccerapp.adapter.SoccerAdapter
import com.example.soccerapp.data.datamodels.Result
import com.example.soccerapp.databinding.FragmentSoccerBinding

class SoccerFragment : Fragment() {

    private val viewModel: SoccerViewModel by activityViewModels()

    private lateinit var binding: FragmentSoccerBinding

    lateinit var adapter: SoccerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSoccerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.xmlmodel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.loadClubs()

        Log.e("---", "Fragment")

        if (viewModel.clubs.value != null) {
            updateAdapter(viewModel.clubs.value!!)
        }

        Log.e("---", "update Adapter")

        viewModel.clubs.observe(
            viewLifecycleOwner,
            Observer {
                updateAdapter(it)
            }
        )
        binding.rvResults.addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))
    }

    fun updateAdapter(list: List<Result>) {
        if (binding.rvResults.adapter != null) {
            adapter.notifyDataSetChanged()
        } else {
            adapter = SoccerAdapter(list)
            binding.rvResults.adapter = adapter
        }
    }
}
