package com.alidoran.mvvm_hilt_room_retro_test.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.alidoran.mvvm_hilt_room_retro_test.databinding.ActivityMyBinding
import com.alidoran.mvvm_hilt_room_retro_test.model.Movie
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMyBinding

    lateinit var movieList:List<Movie>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}