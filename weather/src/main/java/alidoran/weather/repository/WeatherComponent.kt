package alidoran.weather.repository

import alidoran.weather.api.WeatherApi
import alidoran.weather.di.RetroModule
import alidoran.weather.view_model.WeatherMainActivityViewModel
import dagger.Component
import java.security.Provider
import javax.inject.Singleton

@Singleton
@Component(modules = [RetroModule::class])
interface WeatherComponent {
    fun getWeatherApi(): WeatherApi
}