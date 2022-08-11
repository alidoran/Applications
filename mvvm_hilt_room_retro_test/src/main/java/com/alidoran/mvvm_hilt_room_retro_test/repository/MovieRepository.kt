package com.alidoran.mvvm_hilt_room_retro_test.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alidoran.mvvm_hilt_room_retro_test.db.MovieDao
import com.alidoran.mvvm_hilt_room_retro_test.model.Movie
import com.alidoran.mvvm_hilt_room_retro_test.api.MoviesService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieDao: MovieDao,
    private val moviesService: MoviesService
) : MoviesRepositoryInterface {

    val _movies = MutableLiveData<List<Movie>>()
    val movie
        get() = _movies

    override fun observeTop250Movies() = movieDao.observe250TopMovie()

    override suspend fun refreshTop250Movies() {
        withContext(Dispatchers.IO) {
            val response = moviesService.getTop250Movies()
            if (response.isSuccessful) {
                response.body()?.let {
                    movieDao.insertAll(it.items)
                } ?: println("Couldn't load the list")
            } else {
                println("Server data is empty")
            }
        }
    }
}