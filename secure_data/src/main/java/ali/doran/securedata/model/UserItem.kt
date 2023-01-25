package ali.doran.securedata.model

import androidx.room.Entity
import androidx.room.PrimaryKey

//@Entity(tableName = "user_table")
data class UserItem(
//    @PrimaryKey(autoGenerate = true)
    val uid: Int? = null,
    val firstName: String,
    var lastName: String,
    var phoneNumber: String,
    var password: String
)
