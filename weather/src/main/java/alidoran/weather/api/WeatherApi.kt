package alidoran.weather.api

import alidoran.weather.model.WeatherModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("v1/current.json")
    suspend fun getWeatherApi(
        @Query("key")
        key: String = "339a563b08894e889e8125922220706",
        @Query("q")
        q: String,
        @Query("aqi")
        aqi: String = "no"
    ): Response<WeatherModel>
}