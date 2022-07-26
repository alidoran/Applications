package ali.doran.hilt.network

import ali.doran.hilt.data.WeatherService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val BASE_ADDRESS = "https://api.weatherapi.com/"

private fun createBuilder(): Retrofit {
    val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(15, TimeUnit.SECONDS)
        .build()
    return Retrofit.Builder()
        .baseUrl(BASE_ADDRESS)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
}

fun getWeatherService(): WeatherService {
    return createBuilder().create(WeatherService::class.java)
}
