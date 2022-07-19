package com.alidoran.mvvm_dagger_room_retro_test.view

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.alidoran.mvvm_dagger_room_retro_test.databinding.ActivityMdrrtBinding
import com.alidoran.mvvm_dagger_room_retro_test.model.Movie
import com.alidoran.mvvm_dagger_room_retro_test.view_model.MdrrtMainViewModel

class MdrrtActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMdrrtBinding
    private val weatherMainActivityViewModel: MdrrtMainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMdrrtBinding.inflate(layoutInflater)
        setContentView(binding.root)

        weatherMainActivityViewModel.getLiveDate().observe(this) { t ->
            val movieList: List<Movie>? = t
            Log.d(TAG, "onCreate: ${movieList!![0].title}")
        }
    }
}