package ali.doran.hilt.ui

import ali.doran.hilt.R
import ali.doran.hilt.data.WeatherService
import ali.doran.hilt.databinding.ActivityHiltBinding
import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HiltActivity : AppCompatActivity() {

    @Inject
    lateinit var weatherService:WeatherService
    lateinit var binding: ActivityHiltBinding
    private val viewModel: HiltActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHiltBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_hilt)

        viewModel.callWeatherService()
        callWeatherService()
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun callWeatherService(){
        GlobalScope.launch(Dispatchers.IO) {
            val response = weatherService.getWeatherApi2(q = "Tehran")
            if (response.isSuccessful)
                Log.d(ContentValues.TAG, "makeApiCall: ${response.body()!!.location}")
            else
                Log.d(ContentValues.TAG, "makeApiCall: ${response.errorBody()}")
        }
    }
}