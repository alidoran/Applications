package ali.doran.securedata.data.local

import ali.doran.securedata.model.BankCard
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface BankCardDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBankCard(bankCard: BankCard)

    @Delete
    suspend fun deleteBankCard(bankCard: BankCard)

    @Query("SELECT * FROM bank_card_table")
    fun observeAllBankCard(): LiveData<List<BankCard>>
}