package com.alidoran.mvvm_hilt_room_retro_test.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alidoran.mvvm_hilt_room_retro_test.model.Movie
import com.alidoran.mvvm_hilt_room_retro_test.repository.MovieRepository
import com.alidoran.mvvm_hilt_room_retro_test.repository.MoviesRepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MdrrtMainViewModel @Inject constructor(
    private val repository: MoviesRepositoryInterface
) : ViewModel() {

    private val _liveData : LiveData<List<Movie>> = repository.observeTop250Movies()
    val liveData
        get() = _liveData

    private val _eventNetworkError = MutableLiveData<Boolean>(false)
    private val _isNetworkErrorShown = MutableLiveData<Boolean>(false)


    init {
        refresh250MoviesFromRepository()
    }

    fun refresh250MoviesFromRepository() {
        viewModelScope.launch {
            try {
                repository.refreshTop250Movies()
                _eventNetworkError.value = false
                _isNetworkErrorShown.value = false
            } catch (e: Exception) {
                _eventNetworkError.value = true
            }
        }
    }
}