package com.aliasadi.data.entities

import androidx.room.PrimaryKey

/**
 * Created by Ali Asadi on 13/05/2020
 */
data class MovieData(
    @PrimaryKey val id: Int,
    val description: String,
    val image: String,
    val title: String
)