package com.alidoran.mvvm_dagger_room_retro_test.db

import androidx.room.*
import com.alidoran.mvvm_dagger_room_retro_test.model.Movie

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
}