package ali.doran.securedata.data.local

import ali.doran.securedata.model.BankCard
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [BankCard::class],
    version = 1,
    exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
//    abstract fun userDao(): UserDao
    abstract fun bankCardDao(): BankCardDao


    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                val mInstance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "secure_data_database"
                    )
                    .allowMainThreadQueries()
                    .build()
                instance = mInstance
                mInstance
            }
        }
    }
}