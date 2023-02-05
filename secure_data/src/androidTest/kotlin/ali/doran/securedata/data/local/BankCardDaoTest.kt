package ali.doran.securedata.data.local

import ali.doran.securedata.db.AppDatabase
import ali.doran.securedata.getOrAwaitValue
import ali.doran.securedata.model.BankCard
import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.SmallTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertTrue
import java.util.concurrent.Executors

@ExperimentalCoroutinesApi
@SmallTest
class BankCardDaoTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: AppDatabase
    private lateinit var dao: BankCardDao
    private val bankCardItem =
        BankCard(1, "saderat", "8888 8888 8888 8888", "1234", "98", 1234,123, "12345")

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext<Context>(),
            AppDatabase::class.java
        )
            .setTransactionExecutor(Executors.newSingleThreadExecutor())
            .allowMainThreadQueries()
            .build()
        dao = database.bankCardDao()
    }

    @Test
    fun insertBankCard() = runBlocking {
        database.bankCardDao().insertBankCard(bankCardItem)
        val allBankCard = dao.observeAllBankCard().getOrAwaitValue()
        assertTrue(allBankCard.contains(bankCardItem))
    }


    @After
    fun tearDown() {
        database.close()
    }
}