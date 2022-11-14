package com.alidoran.mvvm_hilt_room_retro_test.view

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.MediumTest
import com.alidoran.mvvm_hilt_room_retro_test.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import com.alidoran.mvvm_hilt_room_retro_test.R
import com.alidoran.mvvm_hilt_room_retro_test.repositories.FakeShoppingRepositoryAndroidTest
import com.alidoran.mvvm_hilt_room_retro_test.view_model.Show250TopMovieViewModel
import org.junit.After


@HiltAndroidTest
@MediumTest
@ExperimentalCoroutinesApi
class Show250TopMovieFragmentTest {
    private var navController:NavController? = null

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        hiltRule.inject()

        val navController = mock(NavController::class.java)
        val testViewModel = Show250TopMovieViewModel(FakeShoppingRepositoryAndroidTest())
        launchFragmentInHiltContainer<Show250TopMovieFragment> {
            vm = testViewModel
            Navigation.setViewNavController(requireView(), navController)
        }
    }

    @Test
    fun clickOnRecyclerItem(){
        onView(withId(R.id.recycler_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
    }

    @Test
    fun clickAddMovieButtonNavigateToAddMovieFragment() {
        onView(withId(R.id.fab_add)).perform(click())
        verify(navController)!!.navigate(
            Show250TopMovieFragmentDirections.actionShow250TopMovieFragmentToInsertMovieFragment(3)
        )
    }

    @After
    fun tearDown(){
        navController = null
    }
}