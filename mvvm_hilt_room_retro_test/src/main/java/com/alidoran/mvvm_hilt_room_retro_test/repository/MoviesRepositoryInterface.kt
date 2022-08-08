package com.alidoran.mvvm_hilt_room_retro_test.repository

import androidx.lifecycle.LiveData
import com.alidoran.mvvm_hilt_room_retro_test.model.Movie

interface MoviesRepositoryInterface {
    suspend fun observeTop250Movies(): LiveData<List<Movie>>
    suspend fun refreshTop250Movies()
}