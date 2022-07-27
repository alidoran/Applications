package ali.doran.common_library

import android.util.Log
import java.math.BigInteger
import java.security.*
import javax.crypto.Cipher

class CodingTools {

    private fun stringToSha256(inputString: String): String {
        var stringHashCode = ""
        try {
            val objSHA = MessageDigest.getInstance("SHA-256")
            val bytSHA = objSHA.digest(inputString.toByteArray())
            val intNumber = BigInteger(1, bytSHA)
            stringHashCode = intNumber.toString(16)
            while (stringHashCode.length < 64) {
                stringHashCode = "0$stringHashCode"
            }
        } catch (e: Exception) {
            Log.d("stringToSha256", "stringToSha256: ${e.message}")
        }
        return stringHashCode
    }

    //region Cipher
    @Throws(java.lang.Exception::class)
    fun rSAEncrypt(plain: String): EncryptCipherModel {
        val kpg: KeyPairGenerator = KeyPairGenerator.getInstance("RSA")
        kpg.initialize(2048)
        val kp: KeyPair = kpg.genKeyPair()
        val publicKey: PublicKey = kp.public
        val privateKey: PrivateKey = kp.private
        val cipher: Cipher = Cipher.getInstance("RSA")
        cipher.init(Cipher.ENCRYPT_MODE, publicKey)
        val encryptedBytes: ByteArray = cipher.doFinal(plain.toByteArray())
        return EncryptCipherModel(publicKey, privateKey, encryptedBytes)
    }

    @Throws(java.lang.Exception::class)
    fun rSADecrypt(encryptedBytes: ByteArray?, privateKey: PrivateKey?): String {
        val cipher1: Cipher = Cipher.getInstance("RSA")
        cipher1.init(Cipher.DECRYPT_MODE, privateKey)
        val decryptedBytes: ByteArray = cipher1.doFinal(encryptedBytes)
        val decrypted = String(decryptedBytes)
        println("DDecrypted?????$decrypted")
        return decrypted
    }

    data class EncryptCipherModel(
        val publicKey: PublicKey,
        val privateKey: PrivateKey,
        val encryptedBytes: ByteArray
    )

    //endregion
}