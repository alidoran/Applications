package com.alidoran.mvvm_hilt_room_retro_test.api

import androidx.lifecycle.LiveData
import com.alidoran.mvvm_hilt_room_retro_test.model.Top250Movies
import retrofit2.Response
import retrofit2.http.GET

interface MoviesService {
    @GET("Top250Movies/k_rpwn25xi")
    suspend fun getTop250Movies(): Response<Top250Movies>
}