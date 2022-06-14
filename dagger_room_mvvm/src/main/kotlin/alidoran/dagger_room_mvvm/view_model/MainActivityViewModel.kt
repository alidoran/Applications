package alidoran.dagger_room_mvvm.view_model

import alidoran.dagger_room_mvvm.MyApp
import alidoran.dagger_room_mvvm.database.UserDao
import alidoran.dagger_room_mvvm.database.UserEntity
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import javax.inject.Inject

class MainActivityViewModel(application: Application)
    : AndroidViewModel(application) {

    @Inject
    lateinit var userDao: UserDao

    lateinit var allUserList: MutableLiveData<List<UserEntity>>

        init{
            (application as MyApp).getAppComponent().inject(this)
            allUserList = MutableLiveData()
            getAllRecords()
        }

    fun getRecordObserver(): MutableLiveData<List<UserEntity>>{
        return allUserList
    }

    private fun getAllRecords(){
        val list = userDao.getAllRecord()
        allUserList.postValue(list)
    }

    fun insertRecord(userEntity: UserEntity){
        userDao.insertRecord(userEntity)
        getAllRecords()
    }
}