
import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.alidoran.mvvm_hilt_room_retro_test.MainCoroutineRule
import com.alidoran.mvvm_hilt_room_retro_test.api.MoviesService
import com.alidoran.mvvm_hilt_room_retro_test.db.MovieDao
import com.alidoran.mvvm_hilt_room_retro_test.di.AppModule
import com.alidoran.mvvm_hilt_room_retro_test.repositories.FakeShoppingRepository
import com.alidoran.mvvm_hilt_room_retro_test.repositories.MovieRepositoryDefault
import com.alidoran.mvvm_hilt_room_retro_test.repositories.MoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.internal.managers.ApplicationComponentManager
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import dagger.hilt.components.SingletonComponent
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import javax.inject.Inject

@UninstallModules(AppModule::class)
@HiltAndroidTest
class InsertMovieViewModelAndroidTest {

        private val hiltRule = HiltAndroidRule(this)
        private val instantTaskExecutorRule = InstantTaskExecutorRule()
        private val coroutineRule = MainCoroutineRule()


        @Inject
        lateinit var movieRepository: MoviesRepository


        @get:Rule
        val rule = RuleChain
                .outerRule(hiltRule)
                .around(instantTaskExecutorRule)
                .around(coroutineRule)

        @Before
        fun init() {
                hiltRule.inject()
        }

        @Test
        fun happyPath() {
                Assert.assertTrue(true)
        }

        @Module
        @InstallIn(SingletonComponent::class)
        object TestAppModule{

                @Provides
                fun movieRepository(
                ): MoviesRepository {
                        return FakeShoppingRepository()
                }
        }
}