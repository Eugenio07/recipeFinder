package com.example.recipefinder.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [RecipeDB::class], version = 1)
abstract class RecipeDataBase: RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
}