package com.alidoran.mvvm_hilt_room_retro_test.view_model

import android.content.Intent
import androidx.lifecycle.ViewModel

class ImagePickerViewModel:ViewModel() {

    fun getChooseFileIntent(): Intent{
        val chooseFileIntent = Intent(Intent.ACTION_GET_CONTENT)
        chooseFileIntent.type = "*/*"
        return Intent.createChooser(chooseFileIntent, "Choose a file")
    }
}