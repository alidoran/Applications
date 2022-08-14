package com.alidoran.mvvm_hilt_room_retro_test.view_model

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.alidoran.mvvm_hilt_room_retro_test.repositories.FakeShoppingRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.Rule
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.parallel.Execution
import org.junit.jupiter.api.parallel.ExecutionMode

@ExperimentalCoroutinesApi
@DelicateCoroutinesApi
class InsertMovieViewModelTest {
    private lateinit var myViewModel: InsertMovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    //For calling suspend methods
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")


    @BeforeEach
    fun setup() {
        Dispatchers.setMain(mainThreadSurrogate)
        myViewModel = InsertMovieViewModel(FakeShoppingRepository())
    }


//    @Test
//    fun `insert`() {
//        myViewModel.movie.title = "Insert Test"
//        myViewModel.insertMovie()
//        myViewModel.findMovieByTitle("Insert")
//        val findMovie = myViewModel.liveData.getOrAwaitValue()
//        assertTrue(findMovie.isNotEmpty())
//    }

    @Test
    fun `search movie`() {
        myViewModel.movie.title = "Insert Test"
        myViewModel.insertMovie()
        myViewModel.findMovieByTitle("Insert")
        val findMovie = myViewModel.liveData.getOrAwaitValue()
        assertTrue(findMovie.isNotEmpty())
    }



    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

}
