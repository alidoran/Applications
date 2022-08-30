package com.alidoran.mvvm_hilt_room_retro_test.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alidoran.mvvm_hilt_room_retro_test.model.Movie
import com.alidoran.mvvm_hilt_room_retro_test.repositories.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InsertMovieViewModel @Inject constructor(
    private val repository: MoviesRepository
) : ViewModel() {

    val movie = Movie(isLocal = true)

    fun insertMovie(): Long {
            return repository.insertMovieItem(movie)
    }
}