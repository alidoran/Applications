package alidoran.safelycamera.viewmodel

import alidoran.safelycamera.di.DaggerCommonFactory
import android.app.Application
import android.content.ContentValues
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import alidoran.safelycamera.model.UserModel
import alidoran.safelycamera.tools.easyLogFinder
import alidoran.safelycamera.tools.preferencesName
import alidoran.safelycamera.tools.secretKey
import java.util.regex.Matcher
import java.util.regex.Pattern

class PasswordViewModel(app: Application) : AndroidViewModel(app) {
    private val common = DaggerCommonFactory.create().commonProvider()
    private val sharedPreferences = app.getSharedPreferences(preferencesName, Context.MODE_PRIVATE)
    private val prefUserKey = "UserNameKey"
    private val prefPasswordKey = "Password"
    private val firstConnectKey = "FirstConnect"

    private fun setPrefUser(userModel: UserModel) {
        val shaUser = common.stringToSha256(userModel.userName.lowercase())
        val shaPass = common.stringToSha256(userModel.password)
        sharedPreferences.edit().apply {
            putString(prefUserKey, shaUser)
            putString(prefPasswordKey, shaPass)
        }.apply()
    }

    fun isFirstConnect(): Boolean {
        return sharedPreferences.getBoolean(firstConnectKey, true)
    }

    internal fun userNameChecker(userName: String): Boolean {
        val pattern: Pattern
        val passwordPattern = "^(?=.{3,20}\$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])\$"
        pattern = Pattern.compile(passwordPattern)
        val matcher: Matcher = pattern.matcher(userName)
        return matcher.matches()
    }

    internal fun passwordChecker(password: String): Boolean {
        val pattern: Pattern
        val passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$"
        pattern = Pattern.compile(passwordPattern)
        val matcher: Matcher = pattern.matcher(password)
        return matcher.matches()
    }

    internal fun signUpUser(userModel: UserModel): Boolean {
        if (!passwordChecker(userModel.password))
            return false
        setPrefUser(userModel)
        sharedPreferences.edit().apply { putBoolean(firstConnectKey, false) }.apply()
        return true
    }

    fun isUserAccepted(userModel: UserModel): Boolean {
        val isPassValid = checkValidPass(userModel)
        if (isPassValid)
            secretKey = common.stringToSha256(userModel.password).substring(0,16).toByteArray()
        return isPassValid
    }

    private fun checkValidPass(userModel: UserModel): Boolean {
        var result = false
        val shaUser = common.stringToSha256(userModel.userName.lowercase())
        val shaPass = common.stringToSha256(userModel.password)
        val prefUserName = sharedPreferences.getString(prefUserKey, "")
        val prefPassword = sharedPreferences.getString(prefPasswordKey, "")
        if (prefUserName!!.isEmpty())
            Log.d(ContentValues.TAG, "$easyLogFinder.checkValidUser: This username isn't exist")
        if (prefPassword!!.isNotEmpty() && prefPassword == shaPass && prefUserName == shaUser)
            result = true
        return result
    }
}