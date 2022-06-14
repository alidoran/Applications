package alidoran.safelycamera.model

import android.graphics.Bitmap

data class PhotoRecyclerModel (
    val fileName: String,
    val dateAdded: String,
    val srcImage: Bitmap
)