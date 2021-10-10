package com.example.recipefinder.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CategoryDB(
    @PrimaryKey
    val idCategory: String,
    val strCategory: String?,
    val strCategoryThumb: String?,
    val strCategoryDescription: String?
)