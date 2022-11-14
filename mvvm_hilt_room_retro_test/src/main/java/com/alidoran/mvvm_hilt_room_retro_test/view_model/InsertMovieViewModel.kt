package com.alidoran.mvvm_hilt_room_retro_test.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alidoran.mvvm_hilt_room_retro_test.model.Movie
import com.alidoran.mvvm_hilt_room_retro_test.repositories.MoviesRepository
import com.alidoran.mvvm_hilt_room_retro_test.tools.Event
import com.alidoran.mvvm_hilt_room_retro_test.tools.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class InsertMovieViewModel @Inject constructor(
    private val repository: MoviesRepository
) : ViewModel() {


    private val _insertMovieStatus = MutableLiveData<Event<Resource<Movie>>>()
    val insertMovieStatus
        get() = _insertMovieStatus

    val movie = Movie(isLocal = true)


    fun insertMovie() {
        if (movie.title.isEmpty()){
            _insertMovieStatus.postValue(Event(Resource.error("Fill title please", null)))
            return
        }
        insertMovieToDb()
        _insertMovieStatus.postValue(Event(Resource.success(movie)))
    }

    private fun insertMovieToDb(){
        repository.insertMovieItem(movie)
    }
}