package com.alidoran.mvvm_hilt_room_retro_test.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alidoran.mvvm_hilt_room_retro_test.model.Movie
import com.alidoran.mvvm_hilt_room_retro_test.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MdrrtMainViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    private var liveData: MutableLiveData<List<Movie>> = MutableLiveData()

    private var _eventNetworkError = MutableLiveData<Boolean>(false)
    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)


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


    private fun readTop250Movies() {
//        viewModelScope.launch(Dispatchers.IO) {
//            val response = top250MoviesService.getTop250Movies()
//            if (response.isSuccessful) {
//                liveData.postValue(response.body()!!.items)
//                movieDao.insertAll(response.body()!!.items)
//            }
//            else
//                Log.d(ContentValues.TAG, "makeApiCall: ${response.body()!!.errorMessage}")
//        }
    }


    fun getLiveDate(): MutableLiveData<List<Movie>> {
        return liveData
    }
}