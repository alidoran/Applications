package com.alidoran.mvvm_hilt_room_retro_test.repositories

import androidx.lifecycle.LiveData
import com.alidoran.mvvm_hilt_room_retro_test.model.Movie

interface MoviesRepository {
    fun observeTop250Movies(): LiveData<List<Movie>>
    suspend fun refreshTop250Movies()
    fun insertMovieItem(movie: Movie): Long
    suspend fun deleteMovieItem(movie: Movie)
    fun observeFindMovieByTitle(title: String): LiveData<List<Movie>>
    fun movieCount(): Int
}