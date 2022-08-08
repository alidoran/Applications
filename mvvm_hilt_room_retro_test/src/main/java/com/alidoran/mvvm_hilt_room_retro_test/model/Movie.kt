package com.alidoran.mvvm_hilt_room_retro_test.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class Movie(
    @PrimaryKey
    val id: String,
    val crew: String,
    val fullTitle: String,
    val imDbRating: String,
    val imDbRatingCount: String,
    val image: String,
    val rank: String,
    val title: String,
    val year: String
)