package com.alidoran.mvvm_dagger_room_retro_test.view_model

import android.app.Application
import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.alidoran.mvvm_dagger_room_retro_test.DaggerAppComponent
import com.alidoran.mvvm_dagger_room_retro_test.MyApp
import com.alidoran.mvvm_dagger_room_retro_test.db.MovieDao
import com.alidoran.mvvm_dagger_room_retro_test.model.Movie
import com.alidoran.mvvm_dagger_room_retro_test.repository.DaggerMoviesComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class MdrrtMainViewModel(application: Application) : AndroidViewModel(application) {

    private var top250MoviesService = DaggerMoviesComponent.create().getTop250Movies()
    private var liveData: MutableLiveData<List<Movie>> = MutableLiveData()
    @Inject
    lateinit var movieDao: MovieDao

    init {
        (application as MyApp).getAppComponent().inject(this)
        readTop250Movies()
    }


    private fun readTop250Movies() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = top250MoviesService.getTop250Movies()
            if (response.isSuccessful) {
                liveData.postValue(response.body()!!.items)
                movieDao.insertAll(response.body()!!.items)
            }
            else
                Log.d(ContentValues.TAG, "makeApiCall: ${response.body()!!.errorMessage}")
        }
    }



    fun getLiveDate(): MutableLiveData<List<Movie>>{
        return liveData
    }
}