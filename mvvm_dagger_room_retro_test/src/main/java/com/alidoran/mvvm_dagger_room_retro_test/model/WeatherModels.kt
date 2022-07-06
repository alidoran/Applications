package com.alidoran.mvvm_dagger_room_retro_test.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_entity")
data class WeatherModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id:Int = 0,
    var location: Location,
    var current: Current
)

data class Location(
    var name: String,
    var region: String,
    var lat: Float,
    var lon: Float,
    var tz_id: String,
    var localtime_epoch: String,
    var localtime: String
)

data class Condition(
    var text: String,
    var icon: String,
    var code: Int
)

data class Current(
    var last_updated_epoch: Double,
    var last_updated: String,
    var temp_c: Float,
    var temp_f: Float,
    var is_day: Int,
    var condition: Condition,
    var wind_mph: Float,
    var wind_kph: Float,
    var wind_degree: Int,
    var wind_dir: String,
    var pressure_mb: Float,
    var pressure_in: Float,
    var precip_mm: Float,
    var precip_in: Float,
    var humidity: Int,
    var cloud: Int,
    var feelslike_c: Float,
    var feelslike_f: Float,
    var vis_km: Float,
    var vis_miles: Float,
    var uv: Float,
    var gust_mph: Float,
    var gust_kph: Float,
)
