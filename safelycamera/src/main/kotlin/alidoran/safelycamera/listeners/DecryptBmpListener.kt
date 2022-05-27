package alidoran.safelycamera.listeners

import android.graphics.Bitmap
import android.net.Uri

interface DecryptBmpListener {
    fun load(uri: Uri): Bitmap
}