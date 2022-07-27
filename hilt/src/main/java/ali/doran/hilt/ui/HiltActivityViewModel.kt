package ali.doran.hilt.ui

import ali.doran.hilt.data.WeatherService
import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HiltActivityViewModel @Inject constructor(
    private val weatherService: WeatherService
): ViewModel() {
    fun callWeatherService(){
        viewModelScope.launch(Dispatchers.IO) {
            val response = weatherService.getWeatherApi2(q = "Tehran")
            if (response.isSuccessful)
                Log.d(ContentValues.TAG, "makeApiCall: ${response.body()!!.location}")
            else
                Log.d(ContentValues.TAG, "makeApiCall: ${response.errorBody()}")
        }
    }
}