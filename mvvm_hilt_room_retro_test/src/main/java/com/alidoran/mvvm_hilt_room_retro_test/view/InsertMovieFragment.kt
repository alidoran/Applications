package com.alidoran.mvvm_hilt_room_retro_test.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.alidoran.mvvm_hilt_room_retro_test.databinding.FragmentInsertMovieBinding
import com.alidoran.mvvm_hilt_room_retro_test.view_model.InsertMovieViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InsertMovieFragment : Fragment() {

    private lateinit var binding: FragmentInsertMovieBinding
    private val viewModel : InsertMovieViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInsertMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.model = viewModel.movie

        binding.btnSaveMovie.setOnClickListener {
            viewModel.insertMovie()
        }

        viewModel.findMovieByTitle("The")

        viewModel.liveData.observe(viewLifecycleOwner){
            println(it ?.let { it[0].title } ?: "")
        }
    }


}