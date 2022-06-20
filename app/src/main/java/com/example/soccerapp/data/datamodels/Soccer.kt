package com.example.soccerapp.data.datamodels

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Soccer(

    @PrimaryKey
    val name: String,
    val country: String,
    val value: Int,
    val image: String,
    val european_titles: Int,
)
