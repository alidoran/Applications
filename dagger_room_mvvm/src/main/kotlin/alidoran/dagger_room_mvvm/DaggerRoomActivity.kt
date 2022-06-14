package alidoran.dagger_room_mvvm


import alidoran.dagger_room_mvvm.database.UserEntity
import alidoran.dagger_room_mvvm.databinding.ActivityDaggerRoomBinding
import alidoran.dagger_room_mvvm.view_model.MainActivityViewModel
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.room.Entity

class DaggerRoomActivity : AppCompatActivity() {
    lateinit var binding: ActivityDaggerRoomBinding
    private val viewModel: MainActivityViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDaggerRoomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSave.setOnClickListener {
            val userEntity = UserEntity(name = binding.edtDesc.text.toString())
            viewModel.insertRecord(userEntity)
            binding.txtResult.text = ""
        }
        initViewModel()
    }

    private fun initViewModel() {
        viewModel.getRecordObserver().observe(this, object : Observer<List<UserEntity>> {
            override fun onChanged(t: List<UserEntity>) {
                t.forEach {
                    binding.txtResult.append(it.name + "\n")
                }
            }
        })
    }
}