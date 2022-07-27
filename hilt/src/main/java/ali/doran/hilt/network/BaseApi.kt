package ali.doran.hilt.network

import ali.doran.hilt.data.WeatherService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


open class BaseApi protected constructor(){
    
    private fun createBuilder(baseUrl : String): Retrofit {
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .build()
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    protected fun getWeatherService(): WeatherService {
        val weatherServiceBaseAddress = "https://api.weatherapi.com/"
        return createBuilder(weatherServiceBaseAddress).create(WeatherService::class.java)
    }
}