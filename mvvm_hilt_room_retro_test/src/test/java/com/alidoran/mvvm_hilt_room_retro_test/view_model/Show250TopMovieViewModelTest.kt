package com.alidoran.mvvm_hilt_room_retro_test.view_model

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.alidoran.mvvm_hilt_room_retro_test.model.Movie
import com.alidoran.mvvm_hilt_room_retro_test.repositories.FakeShoppingRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.Rule
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.Mockito

@ExperimentalCoroutinesApi
@DelicateCoroutinesApi
class Show250TopMovieViewModelTest {
    private lateinit var myViewModel: Show250TopMovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    //For calling suspend methods
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")


    @BeforeEach
    fun setup() {
        Dispatchers.setMain(mainThreadSurrogate)
        myViewModel = Show250TopMovieViewModel(FakeShoppingRepository())
    }

    @Test
    fun `Test refresh top 250 movie list`() {
        myViewModel.refresh250MoviesFromRepository()
        val movieList = myViewModel.liveData.getOrAwaitValue()
        assertEquals(movieList[0].id, "TestId")
    }



    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

}
