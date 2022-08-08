package ali.doran.common_library

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class ValidationToolsTest {

    @Test
    fun `Username Shouldn't be empty`() {
        val result = ValidationTools().userNameChecker("")
        assertFalse(result)
    }

    @Test
    fun `Username shouldn't start with underline and dot`(){
        val resultDot = ValidationTools().userNameChecker(".AliDoran")
        val resultUnderline = ValidationTools().userNameChecker("_Doran")
        assertAll(
            {assertFalse(resultDot)},
            {assertFalse(resultUnderline)}
        )
    }

    @Test
    fun `User name should be between 3 to 20 characters`(){
        val resultLessChar = ValidationTools().userNameChecker("Al")
        val resultMoreChar = ValidationTools().userNameChecker("A12345678912345678920")
        assertAll(
            { assertFalse(resultLessChar)},
            { assertFalse(resultMoreChar)}
        )
    }

    @Test
    fun `Username not allowed chain of underline and dot`(){
        val result01 = ValidationTools().userNameChecker("Ali_.Doran")
        val result02 = ValidationTools().userNameChecker("Ali._Doran")
        val result03 = ValidationTools().userNameChecker("Ali__Doran")
        val result04 = ValidationTools().userNameChecker("Ali..Doran")
        assertAll(
            { assertFalse(result01)},
            { assertFalse(result02)},
            { assertFalse(result03)},
            { assertFalse(result04)}
        )
    }

    @Test
    fun `Username allow chars check`(){
        val result01 = ValidationTools().userNameChecker("AliعDoran")
        val result02 = ValidationTools().userNameChecker("Ali=Doran")
        val result03 = ValidationTools().userNameChecker("Ali*Doran")
        val result04 = ValidationTools().userNameChecker("AliDo#ran")
        assertAll(
            { assertFalse(result01)},
            { assertFalse(result02)},
            { assertFalse(result03)},
            { assertFalse(result04)}
        )
    }
}