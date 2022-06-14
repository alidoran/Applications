package alidoran.dagger_room_mvvm

import alidoran.dagger_room_mvvm.di.AppComponent
import alidoran.dagger_room_mvvm.di.AppModule
import alidoran.dagger_room_mvvm.di.DaggerAppComponent
import android.app.Application
import android.hardware.Sensor
import android.hardware.SensorManager
import java.lang.StringBuilder

class MyApp : Application() {
    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    fun getAppComponent(): AppComponent {
        return appComponent
    }
}
