package com.example.recipefinder.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CountryDB(
    @PrimaryKey
    val name: String,
    val demonym: String?,
    val flag: String?
)