package com.example.soccerapp.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.soccerapp.adapter.SoccerAdapter
import com.example.soccerapp.data.datamodels.Soccer
import com.example.soccerapp.databinding.FragmentSoccerBinding

class SoccerFragment : Fragment() {

    private val viewModel: SoccerViewModel by activityViewModels()

    private lateinit var binding: FragmentSoccerBinding

    private lateinit var adapter: SoccerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

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
            viewLifecycleOwner
        ) {
            updateAdapter(it)
        }

        viewModel.isSortByAbc.observe(
            viewLifecycleOwner
        ) {
            if (viewModel.clubs.value != null) {
                if (it) {
                    viewModel.sortByAbc()
                } else {
                    viewModel.sortByValue()
                }
            }
        }
        binding.rvResults.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                LinearLayoutManager.VERTICAL
            )
        )

        binding.toggelBtn.setOnClickListener {
            viewModel.toggleSort()
        }

        val imageList = binding.rvResults

        val imageListAdapter = SoccerAdapter(emptyList())

        imageList.adapter = imageListAdapter

        viewModel.clubs.observe(
            viewLifecycleOwner
        ) {
            imageListAdapter.submitList(it)
        }
    }

    private fun updateAdapter(list: List<Soccer>) {
        adapter = SoccerAdapter(list)
        binding.rvResults.adapter = adapter
    }
}
