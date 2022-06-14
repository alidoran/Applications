package alidoran.dagger_room_mvvm.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UserEntity::class], version = 1)
abstract class AppDataBase: RoomDatabase() {

    abstract fun getUserDao(): UserDao

    companion object{
        @Volatile
        private var db_instance: AppDataBase? = null

        fun getDataBase(context:Context): AppDataBase{
            return db_instance ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "dagger_room_database"
                )
                    .allowMainThreadQueries()
                    .build()
                db_instance = instance
                instance
            }

        }
    }
}