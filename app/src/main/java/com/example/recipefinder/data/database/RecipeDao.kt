package com.example.recipefinder.data.database

import androidx.room.*

@Dao
interface RecipeDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertRecipe(recipe: RecipeDB)

    @Query("SELECT * FROM RecipeDB")
    fun getAll(): List<RecipeDB>

    @Delete
    fun deleteRecipe(recipe: RecipeDB)

    @Query("SELECT COUNT(idMeal) FROM RecipeDB")
    fun recipeCount(): Int
}