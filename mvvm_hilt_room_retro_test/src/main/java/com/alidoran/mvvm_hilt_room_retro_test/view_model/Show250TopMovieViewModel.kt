package com.alidoran.mvvm_hilt_room_retro_test.view_model

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alidoran.mvvm_hilt_room_retro_test.R
import com.alidoran.mvvm_hilt_room_retro_test.model.Movie
import com.alidoran.mvvm_hilt_room_retro_test.repositories.MoviesRepository
import com.squareup.picasso.Picasso
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class Show250TopMovieViewModel @Inject constructor(
    private val repository: MoviesRepository
) : ViewModel() {
    init {
        refresh250MoviesFromRepository()
    }

    private val _liveData : LiveData<List<Movie>> = repository.observeTop250Movies()
    val liveData
        get() = _liveData

    fun refresh250MoviesFromRepository() {
        viewModelScope.launch {
            repository.refreshTop250Movies()
        }
    }

    fun deleteMovie(movie: Movie){
        viewModelScope.launch {
        repository.deleteMovieItem(movie)
        }
    }
}