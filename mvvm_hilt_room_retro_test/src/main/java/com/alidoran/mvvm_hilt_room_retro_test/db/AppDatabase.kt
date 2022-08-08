package com.alidoran.mvvm_hilt_room_retro_test.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.alidoran.mvvm_hilt_room_retro_test.model.Movie


@Database(entities = [Movie::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun moviesDao(): MovieDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                val mInstance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "imdb"
                    )
                    .allowMainThreadQueries()
                    .build()
                instance = mInstance
                mInstance
            }
        }
    }
}