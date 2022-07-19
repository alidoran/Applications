package alidoran.weather.view

import alidoran.weather.databinding.ActivityWeatherMainBinding
import alidoran.weather.view_model.WeatherMainActivityViewModel
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import alidoran.weather.model.WeatherModel
import android.widget.Toast

class WeatherMainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWeatherMainBinding
    private val weatherMainActivityViewModel: WeatherMainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeatherMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        weatherMainActivityViewModel.getLiveDate().observe(this
        ) {
                t -> val mWeatherModel: WeatherModel? = t
            println("AliDoran" + mWeatherModel!!.location.name)
            Toast.makeText(this@WeatherMainActivity,mWeatherModel!!.location.name, Toast.LENGTH_LONG ).show()
        }
    }
}