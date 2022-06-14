package alidoran.safelycamera.model

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.ContentUris
import android.provider.MediaStore
import java.util.ArrayList
import javax.inject.Inject

class FileModelProvider @Inject constructor(){

    @SuppressLint("Range")
    fun getUriFileList(isEncrypt: Boolean, filePath: String, contentResolver: ContentResolver):
            ArrayList<FileModel> {
        var result = ArrayList<FileModel>()
        val contentUri = MediaStore.Files.getContentUri("external")
        val selection = MediaStore.MediaColumns.RELATIVE_PATH + "=?"
        val selectionArgs = arrayOf(filePath)
        val cursor = contentResolver.query(
            contentUri, null, selection, selectionArgs, null)
        if (cursor==null || cursor.count==0){
            cursor?.close()
            return result
        }else{
            while (cursor.moveToNext()){
                cursor.getColumnIndex(MediaStore.MediaColumns.MIME_TYPE)
                val fileModel = FileModel(
                    cursor.getLong(cursor.getColumnIndex(MediaStore.MediaColumns._ID)),
                    cursor.getString(cursor.getColumnIndex(MediaStore.MediaColumns.DISPLAY_NAME)),
                    cursor.getLong(cursor.getColumnIndex(MediaStore.MediaColumns.DATE_ADDED)),
                    ContentUris.withAppendedId(contentUri,
                    cursor.getLong(cursor.getColumnIndex(MediaStore.MediaColumns._ID))),
                    cursor.getString(cursor.getColumnIndex(MediaStore.MediaColumns.MIME_TYPE))
                )
                if (isEncrypt)
                    result.add(fileModel)
                else if(fileModel.mimeType == "image/jpeg")
                    result.add(fileModel)
            }
            return result
        }

    }
}