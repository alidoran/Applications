package alidoran.safelycamera.model

import android.net.Uri

data class FileModel(
    val id: Long,
    val name: String,
    val dateAdded: Long,
    val uri: Uri,
    val mimeType: String
)
