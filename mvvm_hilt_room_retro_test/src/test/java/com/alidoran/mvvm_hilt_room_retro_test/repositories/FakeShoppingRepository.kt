package com.alidoran.mvvm_hilt_room_retro_test.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alidoran.mvvm_hilt_room_retro_test.model.Movie

class FakeShoppingRepository : MoviesRepository {

    private val movieList = ArrayList<Movie>()

    override fun observeTop250Movies(): LiveData<List<Movie>> {
        val value = createMovieList()
        return MutableLiveData(value)
    }

    override suspend fun refreshTop250Movies() {
//        val value = createMovieList()
//        observeMovieList.postValue(value)
    }

    override fun insertMovieItem(movie: Movie):Long {
        val fakeRowId = 100L
        return try {
            movieList.add(movie)
            fakeRowId
        }catch (e:Exception){
            0
        }
    }

    override suspend fun deleteMovieItem(movie: Movie) {
        movieList.remove(movie)
    }

    override fun movieCount(): Int {
        return movieList.count()
    }

    override fun observeFindMovieByTitle(title: String): LiveData<List<Movie>> {
        val foundMovieList = movieList.filter {
                movie: Movie -> movie.title == title
        }
        return MutableLiveData(foundMovieList)
    }

    private fun refreshLiveData(){

        observeTop250Movies()
    }

    private fun createMovieList():List<Movie>{
        val movieList = ArrayList<Movie>()
        for (i in 0..2){
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