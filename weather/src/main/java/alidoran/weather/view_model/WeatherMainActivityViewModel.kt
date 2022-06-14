package alidoran.weather.view_model


import android.app.Application
import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import alidoran.weather.model.WeatherModel
import alidoran.weather.repository.DaggerWeatherComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class WeatherMainActivityViewModel(application: Application) : AndroidViewModel(application) {
    var mService = DaggerWeatherComponent.create().getWeatherApi()
    private var liveData: MutableLiveData<WeatherModel> = MutableLiveData()

    init {
        makeApiCall()
    }

    fun getLiveDate(): MutableLiveData<WeatherModel>{
        return liveData
    }

    private fun makeApiCall(){
        GlobalScope.launch(Dispatchers.IO) {
            val response = mService.getWeatherApi(q = "Tehran")
            if (response.isSuccessful)
                liveData.postValue(response.body())
            else
                Log.d(TAG, "makeApiCall: ${response.errorBody()}")

            val response2 = mService.getWeatherApi(q = "London")
            if (response2.isSuccessful)
                liveData.postValue(response2.body())
            else
                Log.d(TAG, "makeApiCall: ${response.errorBody()}")
        }
    }

}