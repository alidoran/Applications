package ali.doran.securedata.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bank_card_table")
data class BankCard(
    @PrimaryKey(autoGenerate = true)
    val uid: Int? = null,
    var cardName:String,
    var cardNumber : String,
    var cardPassword: String,
    var date: String,
    var firstPass: Long,
    var cvv: Int,
    var secondPass: String
)
