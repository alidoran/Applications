package com.alidoran.mvvm_dagger_room_retro_test.repository

import com.alidoran.mvvm_dagger_room_retro_test.di.RetroModule
import com.alidoran.mvvm_dagger_room_retro_test.services.Top250MoviesService
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RetroModule::class])
interface MoviesComponent {
    fun getTop250Movies(): Top250MoviesService
}