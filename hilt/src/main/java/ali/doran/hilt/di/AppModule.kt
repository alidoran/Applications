package ali.doran.hilt.di

import ali.doran.hilt.network.BaseApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//@InstallIn(AppCompatActivity::class, FragmentActivity::class)
@Module
@InstallIn(SingletonComponent::class)
class AppModule : BaseApi() {

    @Singleton
    @Provides
    fun weatherServiceProvider() = getWeatherService()
}