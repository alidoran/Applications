package alidoran.dagger_room_mvvm.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {

    @Query("SELECT * FROM user_entity ORDER BY id DESC")
    fun getAllRecord(): List<UserEntity>?

    @Insert
    fun insertRecord(userEntity: UserEntity)
}