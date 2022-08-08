package com.alidoran.mvvm_hilt_room_retro_test.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

open class RetrofitServices protected constructor() {
    fun getMoviesService(): MoviesService {
        val movieBaseUrl = "https://imdb-api.com/en/API/"
        return getRetroBuilder()
            .baseUrl(movieBaseUrl)
            .build()
            .create(MoviesService::class.java)
    }

    private fun getRetroBuilder(): Retrofit.Builder {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
    }
}