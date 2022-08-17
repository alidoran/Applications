package com.alidoran.mvvm_hilt_room_retro_test.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alidoran.mvvm_hilt_room_retro_test.model.Movie
import kotlinx.coroutines.coroutineScope

class FakeShoppingRepository : MoviesRepository {

    private val movieList = mutableListOf<Movie>()
    private val observeMovieList = MutableLiveData<List<Movie>>(movieList)

    override fun observeTop250Movies() = observeMovieList

    override suspend fun refreshTop250Movies() {
        val movie = Movie(
            "TestId",
            "TestCrew",
            "TestFullTitle",
            "Test imdb rate",
            "Test imdb ratecount",
            "Test image",
            "Ttest rank",
            "Test title",
            "Test year"
        )
        movieList.add(movie)
        refreshLiveData()
    }

    override suspend fun insertMovieItem(movie: Movie) {
        movieList.add(movie)
        observeMovieList.value = movieList
    }

    override suspend fun deleteMovieItem(movie: Movie) {
        movieList.remove(movie)
        observeMovieList.value = movieList
    }

    override suspend fun observeFindTitleMovie(title: String): LiveData<List<Movie>> {
        val foundMovieList = movieList.filter {
                movie: Movie -> movie.title == title
        }
        return MutableLiveData(foundMovieList)
    }

    private fun refreshLiveData(){
        observeMovieList.postValue(movieList)
    }

}