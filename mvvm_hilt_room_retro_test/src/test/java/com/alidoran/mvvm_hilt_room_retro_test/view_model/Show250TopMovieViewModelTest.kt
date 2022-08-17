package com.alidoran.mvvm_hilt_room_retro_test.view_model

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.liveData
import com.alidoran.mvvm_hilt_room_retro_test.repositories.FakeShoppingRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import org.junit.Rule
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test


@ExperimentalCoroutinesApi
@DelicateCoroutinesApi
class Show250TopMovieViewModelTest {
    private lateinit var viewModel: Show250TopMovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
//
    //For calling suspend methods
    private val mainThreadSurrogate = newSingleThreadContext("ShowThread")


    @BeforeEach
    fun setup() {
        Dispatchers.setMain(mainThreadSurrogate)
        viewModel = Show250TopMovieViewModel(FakeShoppingRepository())
    }


    @Test
    fun `Refresh 250 top movie List`() {
        viewModel.refresh250MoviesFromRepository()
        val movieList = viewModel.liveData.getOrAwaitValue()
        Assertions.assertTrue(movieList.isNotEmpty())

    }


    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

}
