package alidoran.dagger_room_mvvm.di

import alidoran.dagger_room_mvvm.database.AppDataBase
import alidoran.dagger_room_mvvm.database.UserDao
import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val application: Application) {

    @Singleton
    @Provides
    fun getUserDao(appDataBase: AppDataBase): UserDao{
        return appDataBase.getUserDao()
    }

    @Singleton
    @Provides
    fun getRoomDbInstance(): AppDataBase{
        return AppDataBase.getDataBase(provideAppContext())
    }

    @Singleton
    @Provides
    fun provideAppContext(): Context{
        return application.applicationContext
    }
}