package ali.doran.securedata.di

import ali.doran.securedata.data.local.AppDatabase.Companion.getDatabase
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun roomProvider(@ApplicationContext appContext: Context) = getDatabase(appContext)

}