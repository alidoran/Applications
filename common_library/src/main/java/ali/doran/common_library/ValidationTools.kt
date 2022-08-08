package ali.doran.common_library

import java.util.regex.Matcher
import java.util.regex.Pattern

class ValidationTools {

    fun userNameChecker(userName: String): Boolean {
        val userNamePattern = "^(?=.{3,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$"
        val pattern = Pattern.compile(userNamePattern)
        val matcher = pattern.matcher(userName)
        return matcher.matches()
    }

    fun usernameErrorCause(userName: String) : String{
        val result = StringBuilder()
        if (!Pattern.compile("(.{3,20})").matcher(userName).matches())
            result.append("The length of the value have to include 3 to 20 characters\n")
        if (Pattern.compile("^[_.]").matcher(userName).find())
            result.append("The username couldn't start with _ or . characters\n")
        if (Pattern.compile("(.*[_.]{2})").matcher(userName).find())
            result.append("The username couldn't include of { '__' , '_.' , '._' , '..'}\n")
        if (Pattern.compile("[^a-zA-Z0-9._]").matcher(userName).find())
            result.append("The username only can include of normal character such as {a-Z A-Z _ .}\n")
        if (Pattern.compile("[_.]$").matcher(userName).find())
            result.append("The username can not finish with this characters {_ .}\n")
        return result.trimEnd().toString()
    }
}