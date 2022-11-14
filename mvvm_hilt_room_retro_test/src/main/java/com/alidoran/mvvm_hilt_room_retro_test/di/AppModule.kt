package com.alidoran.mvvm_hilt_room_retro_test.di

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.alidoran.mvvm_hilt_room_retro_test.api.RetrofitServices
import com.alidoran.mvvm_hilt_room_retro_test.db.AppDatabase
import com.alidoran.mvvm_hilt_room_retro_test.db.MovieDao
import com.alidoran.mvvm_hilt_room_retro_test.repositories.MovieRepositoryDefault
import com.alidoran.mvvm_hilt_room_retro_test.api.MoviesService
import com.alidoran.mvvm_hilt_room_retro_test.repositories.MoviesRepository
import com.alidoran.mvvm_hilt_room_retro_test.tools.ShowWait
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.EarlyEntryPoint
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule : RetrofitServices() {
    @Singleton
    @Provides
    fun movieDao(@ApplicationContext context: Context): MovieDao {
        return AppDatabase.getDatabase(context).moviesDao()
    }

    @Singleton
    @Provides
    fun movieService(): MoviesService {
        return getMoviesService()
    }

    @Singleton
    @Provides
    fun movieRepository(
        movieDao: MovieDao,
        moviesService: MoviesService
    ): MoviesRepository {
        return MovieRepositoryDefault(movieDao, moviesService)
    }
}