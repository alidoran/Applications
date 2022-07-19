package ali.doran.hilt.retro_standard

import retrofit2.Response
import retrofit2.http.*

    interface WeatherService {
        @GET("v1/current.json")
        suspend fun getWeatherApi2(
            @Query("key")
            key: String = "339a563b08894e889e8125922220706",
            @Query("q")
            q: String,
            @Query("aqi")
            aqi: String = "no"
        ): Response<WeatherModel>
    }