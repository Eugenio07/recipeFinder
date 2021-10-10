package com.example.recipefinder.data.database.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.recipefinder.data.database.dao.CategoryDao
import com.example.recipefinder.data.database.dao.CountryDao
import com.example.recipefinder.data.database.dao.IngredientDao
import com.example.recipefinder.data.database.dao.RecipeDao
import com.example.recipefinder.data.database.entity.CategoryDB
import com.example.recipefinder.data.database.entity.CountryDB
import com.example.recipefinder.data.database.entity.IngredientDB
import com.example.recipefinder.data.database.entity.RecipeDB

@Database(
    entities = [RecipeDB::class, CategoryDB::class, CountryDB::class, IngredientDB::class],
    version = 2
)
abstract class RecipeDataBase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
    abstract fun categoryDao(): CategoryDao
    abstract fun countryDao(): CountryDao
    abstract fun ingredientDao(): IngredientDao

    companion object {

        @Volatile
        private var INSTANCE: RecipeDataBase? = null

        fun getInstance(context: Context): RecipeDataBase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        RecipeDataBase::class.java,
                        "database-recipe"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}