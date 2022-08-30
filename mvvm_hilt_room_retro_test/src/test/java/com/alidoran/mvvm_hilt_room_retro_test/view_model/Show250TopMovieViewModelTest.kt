package com.alidoran.mvvm_hilt_room_retro_test.view_model

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.alidoran.mvvm_hilt_room_retro_test.repositories.FakeShoppingRepository
import com.example.android.architecture.blueprints.todoapp.MainCoroutineRule
import getOrAwaitValue
import kotlinx.coroutines.*
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Rule
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtensionContext


@ExperimentalCoroutinesApi
@DelicateCoroutinesApi
class Show250TopMovieViewModelTest {
    private lateinit var viewModel: Show250TopMovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setup() {
        viewModel = Show250TopMovieViewModel(FakeShoppingRepository())
    }


    @Test
    fun `Refresh 250 top movie List`() {
        viewModel.refresh250MoviesFromRepository()
        val movieList = viewModel.getLiveData().getOrAwaitValue()
        Assertions.assertTrue(movieList.isNotEmpty())
    }

}
