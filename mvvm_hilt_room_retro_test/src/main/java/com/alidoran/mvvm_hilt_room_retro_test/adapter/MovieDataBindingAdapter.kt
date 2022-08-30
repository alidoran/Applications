package com.alidoran.mvvm_hilt_room_retro_test.adapter

import android.graphics.Bitmap
import android.net.Uri
import android.view.View
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.alidoran.mvvm_hilt_room_retro_test.R
import com.alidoran.mvvm_hilt_room_retro_test.model.Movie
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception


@BindingAdapter("bind:picasso")
fun picasso(imageView: ImageView, url: String) {
    if (url.isNotEmpty())
    Picasso.get()
        .load(Uri.parse(url))
        .fit()
        .error(R.drawable.baseline_file_download_off_black_24dp)
        .into(imageView)
}