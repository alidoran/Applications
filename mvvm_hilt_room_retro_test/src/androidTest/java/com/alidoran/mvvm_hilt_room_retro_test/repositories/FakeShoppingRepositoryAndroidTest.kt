package com.alidoran.mvvm_hilt_room_retro_test.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alidoran.mvvm_hilt_room_retro_test.model.Movie

class FakeShoppingRepositoryAndroidTest : MoviesRepository {
    private var movieList = createMovieList()
    private val _movieLiveData = MutableLiveData<List<Movie>>(movieList)
    private val liveData = _movieLiveData


    override fun observeTop250Movies() = liveData

    override suspend fun refreshTop250Movies() {
        movieList.clear()
        movieList = createMovieList()
    }

    override fun insertMovieItem(movie: Movie): Long {
        return try {
            movieList.add(movie)
            movieList.lastIndex.toLong()
        } catch (e: Exception) {
            0
        }
    }

    override suspend fun deleteMovieItem(movie: Movie) {
        movieList.remove(movie)
    }

    override fun movieCount(): Int =
        movieList.count()


    override fun observeFindMovieByTitle(title: String): LiveData<List<Movie>> {
        val foundMovieList = movieList.filter { movie: Movie ->
            movie.title == title
        }
        return MutableLiveData(foundMovieList)
    }

    private fun createMovieList(): ArrayList<Movie> {
        val movieList = ArrayList<Movie>()
        for (i in 0..2) {
            val movie = Movie(
                "TestId$i",
                "TestCrew$i",
                "TestFullTitle$i",
                "Test imdb rate$i",
                "Test imdb ratecount$i",
                "Test image$i",
                "Ttest rank$i",
                "Test title$i",
                "Test year$i"
            )
            movieList.add(movie)
        }
        return movieList
    }

}