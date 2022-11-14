package com.alidoran.mvvm_hilt_room_retro_test.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alidoran.mvvm_hilt_room_retro_test.adapter.MovieListAdapter
import com.alidoran.mvvm_hilt_room_retro_test.model.Movie
import com.alidoran.mvvm_hilt_room_retro_test.repositories.MoviesRepository
import com.alidoran.mvvm_hilt_room_retro_test.view.Show250TopMovieFragmentDirections
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class Show250TopMovieViewModel @Inject constructor(
    private val repository: MoviesRepository
) : ViewModel() {

    private val _showWaitLive = MutableLiveData(false)
    val showWaitLive
        get() = _showWaitLive

    fun refresh250MoviesFromRepository() =
        viewModelScope.launch {
            repository.refreshTop250Movies(showWaitLive)
        }

    fun deleteMovie(movie: Movie) =
        viewModelScope.launch {
            repository.deleteMovieItem(movie)
        }

    fun getLiveData(): LiveData<List<Movie>> =
        repository.observeTop250Movies()

    private fun getMovieCount() = repository.movieCount()

    fun getAddMovieAction():NavDirections{
        return Show250TopMovieFragmentDirections
            .actionShow250TopMovieFragmentToInsertMovieFragment(getMovieCount())
    }
}