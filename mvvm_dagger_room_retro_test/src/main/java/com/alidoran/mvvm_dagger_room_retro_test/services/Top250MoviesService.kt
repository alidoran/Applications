package com.alidoran.mvvm_dagger_room_retro_test.services

import com.alidoran.mvvm_dagger_room_retro_test.model.Top250Movies
import retrofit2.Response
import retrofit2.http.GET

interface Top250MoviesService {
    @GET("Top250Movies/k_rpwn25xi")
    suspend fun getTop250Movies(
    ):Response<Top250Movies>
}