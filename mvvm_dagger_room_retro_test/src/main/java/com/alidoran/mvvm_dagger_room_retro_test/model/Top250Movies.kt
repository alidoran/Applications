package com.alidoran.mvvm_dagger_room_retro_test.model

import java.net.IDN

data class Top250Movies(
    val items : List<Movie>,
    val errorMessage: String
)
