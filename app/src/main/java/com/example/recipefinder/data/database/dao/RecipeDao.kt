package com.example.recipefinder.data.database.dao

import androidx.room.*
import com.example.recipefinder.data.database.entity.RecipeDB

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

    @Query("SELECT * FROM RecipeDB WHERE idMeal = :id")
    fun findById(id: String): RecipeDB?

}