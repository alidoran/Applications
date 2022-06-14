package alidoran.safelycamera.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import android.content.ContentValues
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.provider.MediaStore.MediaColumns.*
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import androidx.core.content.PermissionChecker
import androidx.databinding.BindingAdapter
import androidx.lifecycle.AndroidViewModel
import alidoran.safelycamera.tools.secretKey
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.OutputStream
import java.text.SimpleDateFormat
import java.util.*
import javax.crypto.Cipher
import javax.crypto.CipherInputStream
import javax.crypto.spec.SecretKeySpec
import kotlin.collections.ArrayList

class MainPhotoViewModel(private val app: Application) : AndroidViewModel(app) {

    @SuppressLint("SimpleDateFormat")
    fun createFile(isEncrypt: Boolean): Uri {
        val timeStamp = SimpleDateFormat("yyMMdd_HHmmss").format(Date())
        val fileSuffix = if (isEncrypt) ".enc" else ""
        val mimeType = if (isEncrypt) "*/*" else "image/jpeg"
        val filePath = getFilePath(isEncrypt)
        val contentValues = ContentValues()
        contentValues.put(DISPLAY_NAME,"$timeStamp$fileSuffix")
        contentValues.put(MIME_TYPE, mimeType)
        contentValues.put(RELATIVE_PATH, filePath)
        contentValues.put(DATE_TAKEN, System.currentTimeMillis())
        return app.contentResolver
            .insert(MediaStore.Files.getContentUri("external"), contentValues)!!
    }

    internal fun getFilePath(isEncrypt: Boolean): String {
        var filePath = Environment.DIRECTORY_DOCUMENTS + File.separator
        filePath +=
            if (isEncrypt) "SafelyCameraEncrypted"
            else "SafelyCameraPicture"
        filePath += File.separator
        return filePath
    }

    internal fun permissionDisableList(permissionList: ArrayList<String>):ArrayList<String>{
        val result = ArrayList<String>(permissionList.size)
        permissionList.forEach{
            val permissionResult = ContextCompat.checkSelfPermission(app,it)
            if (permissionResult != PermissionChecker.PERMISSION_GRANTED)
                result.add(it)
        }
        return result
    }

    fun decryptBmp(uri:Uri): Bitmap{
        val byteArray = uriToByteArray(uri, false)
        return BitmapFactory.decodeByteArray(byteArray, 0 , byteArray.size)
    }

    private fun uriToByteArray(uri: Uri, isEncrypt: Boolean): ByteArray {
        val cipherMode =
            if (isEncrypt) Cipher.ENCRYPT_MODE
            else Cipher.DECRYPT_MODE
        val buffer = ByteArray(1024)
        var length: Int
        val inputStream = app.baseContext.contentResolver.openInputStream(uri)
        val byteArrayOutputStream = ByteArrayOutputStream()
        val secretKeySpec = SecretKeySpec(secretKey, "AES")
        val cipher = Cipher.getInstance("AES")
        cipher.init(cipherMode, secretKeySpec)
        val cipherInputStream = CipherInputStream(inputStream, cipher)
        while (cipherInputStream.read(buffer).also { length = it } != -1){
            byteArrayOutputStream.write(buffer, 0, length)
        }
        cipherInputStream.close()
        inputStream!!.close()
        return byteArrayOutputStream.toByteArray()

    }

    fun encryption(uri:Uri):Uri{
        val buffer = ByteArray(1024)
        var length: Int
        val resultUri = createFile(true)
        val inputStream = app.baseContext.contentResolver.openInputStream(uri)
        val myOutput: OutputStream? = app.baseContext.contentResolver.openOutputStream(resultUri)
        val sks = SecretKeySpec(secretKey, "AES")
        val cipher = Cipher.getInstance("AES")
        cipher.init(Cipher.ENCRYPT_MODE, sks)
        val cis = CipherInputStream(inputStream, cipher)
        while (cis.read(buffer).also { length = it } != -1) {
            myOutput!!.write(buffer, 0, length)
        }
        cis.close()
        inputStream!!.close()
        return resultUri
    }

    companion object{
        @JvmStatic
        @BindingAdapter("bind:setBitmap")
        fun setBitmap(imageView: AppCompatImageView, bitmap: Bitmap){
            imageView.setImageBitmap(bitmap)
        }
    }
}