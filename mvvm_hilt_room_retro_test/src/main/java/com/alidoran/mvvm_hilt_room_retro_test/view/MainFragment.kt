package com.alidoran.mvvm_hilt_room_retro_test.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.alidoran.mvvm_hilt_room_retro_test.R
import com.alidoran.mvvm_hilt_room_retro_test.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    lateinit var binding :FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initEvent()
    }

    private fun initEvent() {
        binding.btnInsertMovie.setOnClickListener{
            Navigation.findNavController(binding.root)
                .navigate(R.id.action_mainFragment_to_insertMovieFragment)
        }
        binding.btnTop250Movie.setOnClickListener {
            Navigation.findNavController(binding.root)
                .navigate(R.id.action_mainFragment_to_show250TopMovieFragment)
        }
    }
}