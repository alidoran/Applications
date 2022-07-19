package alidoran.weather.repository

import alidoran.weather.service.WeatherService
import alidoran.weather.di.RetroModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RetroModule::class])
interface WeatherComponent {
    fun getWeatherApi(): WeatherService
}