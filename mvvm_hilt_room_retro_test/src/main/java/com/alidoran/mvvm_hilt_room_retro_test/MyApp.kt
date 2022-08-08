package com.alidoran.mvvm_hilt_room_retro_test

import android.app.Application
import androidx.multidex.MultiDexApplication
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class MyApp : MultiDexApplication()
