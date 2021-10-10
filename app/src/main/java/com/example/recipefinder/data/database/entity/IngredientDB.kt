package com.example.recipefinder.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class IngredientDB(
    @PrimaryKey
    val idIngredient: String,
    val strIngredient: String?,
    val strDescription: String?,
    val strType: String?
)