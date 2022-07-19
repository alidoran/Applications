package com.alidoran.mvvm_dagger_room_retro_test.di

import com.alidoran.mvvm_dagger_room_retro_test.services.Top250MoviesService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class RetroModule {
    private val baseUrl = "https://imdb-api.com/en/API/"

    @Singleton
    @Provides
    fun getMoviesApi(retrofit: Retrofit): Top250MoviesService {
        return retrofit.create(Top250MoviesService::class.java)
    }

    @Singleton
    @Provides
    fun getInstance(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}