//package com.alidoran.mvvm_hilt_room_retro_test.view_model
//
//
//import androidx.arch.core.executor.testing.InstantTaskExecutorRule
//import androidx.room.Room
//import androidx.test.platform.app.InstrumentationRegistry
//import com.alidoran.mvvm_hilt_room_retro_test.MainCoroutineRule
//import com.alidoran.mvvm_hilt_room_retro_test.db.AppDatabase
//import com.alidoran.mvvm_hilt_room_retro_test.getOrAwaitValue
//import com.alidoran.mvvm_hilt_room_retro_test.repositories.MoviesRepository
//import dagger.hilt.android.testing.HiltAndroidRule
//import kotlinx.coroutines.DelicateCoroutinesApi
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.ExperimentalCoroutinesApi
//import kotlinx.coroutines.newSingleThreadContext
//import kotlinx.coroutines.test.resetMain
//import kotlinx.coroutines.test.runBlockingTest
//import org.junit.After
//import org.junit.Before
//import org.junit.Rule
//import org.junit.Test
//
//import org.junit.rules.RuleChain
//import javax.inject.Inject
//
//
//@ExperimentalCoroutinesApi
//@DelicateCoroutinesApi
//class Show250TopMovieViewModelTest {
//
//    private lateinit var appDatabase: AppDatabase
//    private lateinit var myViewModel: Show250TopMovieViewModel
//    private val hiltRule = HiltAndroidRule(this)
//    private val instantTaskExecutorRule = InstantTaskExecutorRule()
//    private val coroutineRule = MainCoroutineRule()
//
//    @get:Rule
//    val rule = RuleChain
//        .outerRule(hiltRule)
//        .around(instantTaskExecutorRule)
//        .around(coroutineRule)
//
//    @Inject
//    lateinit var repository: MoviesRepository
//
//    //For calling suspend methods
//    private val mainThreadSurrogate = newSingleThreadContext("UI thread")
//
//
//    @Before
//    fun setup() {
//        hiltRule.inject()
//        val context = InstrumentationRegistry.getInstrumentation().targetContext
//        appDatabase = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
//        myViewModel = Show250TopMovieViewModel(repository)
//    }
//
//    @After
//    fun tearDown(){
//        appDatabase.close()
//    }
//
//    @Test
//    fun `testRefresh250TopMovieList`() {
////        myViewModel.refresh250MoviesFromRepository()
////        val movieList = myViewModel.liveData.getOrAwaitValue()
////        assertEquals(movieList[0].id, "TestId")
//    }
//
////    @Test
////    fun a(){
////        coroutineRule..runBlockingTest {
////            ////    @Test
////    fun `insert`() {
////        myViewModel.movie.title = "Insert Test"
////        myViewModel.insertMovie()
////        myViewModel.findMovieByTitle("Insert")
////        val findMovie = myViewModel.liveData.getOrAwaitValue()
////        assertTrue(findMovie.isNotEmpty())
////    }
////        }
////    }
//
//}
