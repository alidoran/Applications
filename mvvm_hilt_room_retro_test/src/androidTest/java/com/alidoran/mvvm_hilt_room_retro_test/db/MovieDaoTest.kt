package com.alidoran.mvvm_hilt_room_retro_test.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.alidoran.mvvm_hilt_room_retro_test.launchFragmentInHiltContainer
import com.alidoran.mvvm_hilt_room_retro_test.model.Movie
import com.alidoran.mvvm_hilt_room_retro_test.view.Show250TopMovieFragment
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.*
import javax.inject.Inject
import javax.inject.Named

@ExperimentalCoroutinesApi
@SmallTest
@HiltAndroidTest
class MovieDaoTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    @Named("test_db")
    lateinit var database: AppDatabase
    private lateinit var dao: MovieDao

    @Before
    fun setup() {
        hiltRule.inject()
        dao = database.moviesDao()
    }

    @Test
    fun insertDataBase() {
        val movie = Movie(title = "SampleTitle")
        runBlocking {
            database.moviesDao().insertItem(movie)
        }
        val readMovie =
            database.moviesDao().observeFindMovieByTitle("SampleTitle").getOrAwaitValue()
        Assert.assertEquals(readMovie, arrayListOf(movie))
    }

    @After
    fun tearDown() {
        database.close()
    }
}