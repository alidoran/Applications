package alidoran.dagger_room_mvvm.di

import alidoran.dagger_room_mvvm.view_model.MainActivityViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component (modules = [AppModule::class])
interface AppComponent {
    fun inject(mainActivityViewModel: MainActivityViewModel)
}