package com.alidoran.mvvm_hilt_room_retro_test.view

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.alidoran.mvvm_hilt_room_retro_test.databinding.ActivityMdrrtBinding
import com.alidoran.mvvm_hilt_room_retro_test.model.Movie
import com.alidoran.mvvm_hilt_room_retro_test.view_model.MdrrtMainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MdrrtActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMdrrtBinding
    private val viewModel: MdrrtMainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMdrrtBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        viewModel.getLiveDate().observe(this) { t ->
//            val movieList: List<Movie>? = t
//            Log.d(TAG, "onCreate: ${movieList!![0].title}")
//        }

        viewModel.refresh250MoviesFromRepository()
        viewModel.getLiveDate()
    }
}