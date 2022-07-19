package ali.doran.hilt.ui

import ali.doran.hilt.R
import ali.doran.hilt.databinding.ActivityHiltBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HiltActivity : AppCompatActivity() {

    lateinit var binding : ActivityHiltBinding
    val viewModel : HiltActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHiltBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_hilt)

        viewModel.printMString()
    }
}