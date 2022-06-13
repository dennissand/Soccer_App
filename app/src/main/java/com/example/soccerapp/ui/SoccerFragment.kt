package com.example.soccerapp.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.soccerapp.adapter.SoccerAdapter
import com.example.soccerapp.databinding.FragmentSoccerBinding

class SoccerFragment : Fragment() {

    private val viewModel: SoccerViewModel by viewModels()

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

        Log.e("---","Fragment" )

        viewModel.clubs.observe(
            viewLifecycleOwner,
            Observer {
                if (this::adapter.isInitialized) {
                    adapter.notifyDataSetChanged()
                } else {
                    adapter = SoccerAdapter(it)
                    binding.rvResults.adapter = adapter
                }
            }
        )
    }
}
