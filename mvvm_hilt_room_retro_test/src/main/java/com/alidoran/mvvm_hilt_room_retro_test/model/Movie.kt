package com.alidoran.mvvm_hilt_room_retro_test.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class Movie(
    @PrimaryKey
    val id: String="",
    val crew: String="",
    var fullTitle: String="",
    val imDbRating: String="",
    val imDbRatingCount: String="",
    val image: String="",
    var rank: String="",
    var title: String="",
    var year: String="",
    val isLocal: Boolean = false
)