package com.alidoran.mvvm_hilt_room_retro_test.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.alidoran.mvvm_hilt_room_retro_test.model.Movie

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(movie: Movie)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieList(movieList: List<Movie>)

    @Update
    fun update(vararg movie: Movie)

    @Delete
    fun delete(movie: Movie)

    @Query("SELECT * FROM movie")
    fun observe250TopMovie(): LiveData<List<Movie>>

    @Query("SELECT * FROM movie WHERE INSTR(title, :title)>0")
    fun observeFindMovieByTitle(title: String): LiveData<List<Movie>>
}