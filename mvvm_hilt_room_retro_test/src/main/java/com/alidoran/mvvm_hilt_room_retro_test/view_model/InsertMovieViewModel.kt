package com.alidoran.mvvm_hilt_room_retro_test.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alidoran.mvvm_hilt_room_retro_test.model.Movie
import com.alidoran.mvvm_hilt_room_retro_test.repositories.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InsertMovieViewModel @Inject constructor(
    private val repository: MoviesRepository
) : ViewModel() {

    private lateinit var _liveData: LiveData<List<Movie>>
    val liveData
        get() = _liveData


    val movie = Movie(isLocal = true)

    fun insertMovie() {
        viewModelScope.launch {
            repository.insertMovieItem(movie)
        }
    }

    fun findMovieByTitle(title: String) {
        viewModelScope.launch {
            _liveData = repository.observeFindTitleMovie(title)
        }
    }
}