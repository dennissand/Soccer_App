package com.example.soccerapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import coil.load
import com.example.soccerapp.R
import com.example.soccerapp.databinding.DetailSoccerBinding

class SoccerDetail : Fragment() {
    private lateinit var binding: DetailSoccerBinding
    private val viewModel: SoccerViewModel by activityViewModels()

    private var clubIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            clubIndex = it.getInt("clubIndex")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.detail_soccer, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val club = viewModel.clubs.value?.get(clubIndex)

        binding.tvCountryDetail.text = club?.country
        binding.tvTextDetail.text = getString(R.string.text_detail, club?.name, club?.country, club?.value, club?.european_titles)
        binding.clubToolbar.text = getString(R.string.club_name_toolbar, club?.name)

        binding.backBtn.setOnClickListener {
            findNavController().navigateUp()
        }

        val imgUri = club?.image?.toUri()?.buildUpon()?.scheme("https")?.build()

        binding.ivImageDetail.load(imgUri)
    }
}
