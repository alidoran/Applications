package com.alidoran.mvvm_hilt_room_retro_test.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.alidoran.mvvm_hilt_room_retro_test.model.Movie

@Dao
interface MovieDao {
    @Insert
    fun insert(movieList: List<Movie>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(movieList: List<Movie>)

    @Update
    fun update(vararg movie: Movie)

    @Delete
    fun delete(movie: Movie)

    @Query("SELECT * FROM movie")
    fun read250TopMovie(): LiveData<List<Movie>>
}