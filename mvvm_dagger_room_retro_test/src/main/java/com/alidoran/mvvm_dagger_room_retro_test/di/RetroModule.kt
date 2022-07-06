package com.alidoran.mvvm_dagger_room_retro_test.di

import com.alidoran.mvvm_dagger_room_retro_test.services.WeatherApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class RetroModule {
    private val baseUrl = "https://api.weatherapi.com/"

    @Singleton
    @Provides
    fun getWeatherApi(retrofit: Retrofit): WeatherApi {
        return retrofit.create(WeatherApi::class.java)
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