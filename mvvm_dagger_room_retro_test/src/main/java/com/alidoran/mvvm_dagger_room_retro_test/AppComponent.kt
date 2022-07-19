package com.alidoran.mvvm_dagger_room_retro_test

import com.alidoran.mvvm_dagger_room_retro_test.view_model.MdrrtMainViewModel
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(mdrrtMainViewModel: MdrrtMainViewModel)
}