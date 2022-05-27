package alidoran.safelycamera.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.alidoran.safelycamera.R
import com.alidoran.safelycamera.databinding.ActivityPasswordBinding
import alidoran.safelycamera.model.UserModel
import alidoran.safelycamera.viewmodel.PasswordViewModel
import kotlinx.coroutines.*
import java.util.concurrent.TimeUnit
import kotlin.system.exitProcess

class PasswordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPasswordBinding
    private val passwordViewModel: PasswordViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        initEvent()
    }

    private fun initView() {
        if (passwordViewModel.isFirstConnect()) {
            binding.txtTitle.text = getString(R.string.sign_up_details)
            binding.btnOk.text = getString(R.string.signup)
            binding.toolbar.setNavigationIcon(R.drawable.baseline_app_registration_white_24dp)
        } else {
            binding.txtTitle.text = getString(R.string.login_details)
            binding.btnOk.text = getString(R.string.sign_in)
            binding.toolbar.setNavigationIcon(R.drawable.baseline_login_white_24dp)
        }
    }

    private fun initEvent() {
        binding.btnOk.setOnClickListener{
            val userModel =
                UserModel(binding.edtUserName.text.toString(), binding.edtPassword.text.toString())
            if (isUsernameValid(userModel.userName) && isPasswordValid(userModel.password))
                okClicked(userModel)
        }
    }

    private fun isUsernameValid(username: String): Boolean {
        val isUsernameValid = passwordViewModel.userNameChecker(username)
        if (!isUsernameValid) {
            binding.tilUserName.isErrorEnabled
            binding.tilUserName.error = getString(R.string.username_error)
        }
        return isUsernameValid
    }

    private fun isPasswordValid(username: String): Boolean {
        val passwordValid = passwordViewModel.passwordChecker(username)
        if (!passwordValid) {
            binding.tilPassword.isErrorEnabled
            binding.tilPassword.error = getString(R.string.password_error)
        }
        return passwordValid
    }

    private fun okClicked(userModel: UserModel) {
        if (passwordViewModel.isFirstConnect()) {
            val isUserCreated = passwordViewModel.signUpUser(userModel)
            if (isUserCreated) {
                Toast.makeText(this, "User created", Toast.LENGTH_LONG).show()
                startActivity(Intent(this, MainPhotoActivity::class.java))
            } else
                Toast.makeText(this, "User isn't created", Toast.LENGTH_LONG).show()
        } else {
            val isValidPass = passwordViewModel.isUserAccepted(userModel)
            if (isValidPass) startActivity(Intent(this, MainPhotoActivity::class.java))
            else Toast.makeText(this, "Incorrect Password", Toast.LENGTH_LONG).show()
        }
    }

    override fun onBackPressed() {
        if (binding.progressExit.progress in 1..5) {
            val homeIntent = Intent(Intent.ACTION_MAIN)
            homeIntent.addCategory(Intent.CATEGORY_HOME)
            homeIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(homeIntent)
            finish()
            exitProcess(0)
        }else {
            Toast.makeText(this, getString(R.string.press_again_to_exit),
                Toast.LENGTH_LONG).show()
            binding.progressExit.min = 0
            binding.progressExit.max = 5
            binding.progressExit.visibility = View.VISIBLE
            runBlocking {
                progressExitScope()
            }
        }
    }

    private suspend fun progressExitScope()= CoroutineScope(Dispatchers.IO).launch {
        var i = 0
        while (true) {
            if (i <= binding.progressExit.max) {
                delay(TimeUnit.SECONDS.toMillis(1))
                binding.progressExit.progress = i
                i++
            }else{
                binding.progressExit.progress = 0
                binding.progressExit.visibility = View.INVISIBLE
                break
            }
        }
    }
}