package alidoran.safelycamera.tools

import android.content.ContentValues
import android.util.Log
import java.math.BigInteger
import java.security.MessageDigest
import javax.inject.Inject

class Common @Inject constructor() {
    fun stringToSha256(inputString: String): String {
        var stringHashCode = StringBuilder()
        try {
            val objSHA = MessageDigest.getInstance("SHA-256")
            val bytSHA = objSHA.digest(inputString.toByteArray())
            val intNumber = BigInteger(1, bytSHA)
            stringHashCode = StringBuilder(intNumber.toString(16))
            while (stringHashCode.length < 64) {
                stringHashCode.insert(0, "0")
            }
        } catch (e: java.lang.Exception) {
            Log.d(ContentValues.TAG, "stringToSha256:$e")
        }
        return stringHashCode.toString()
    }
}