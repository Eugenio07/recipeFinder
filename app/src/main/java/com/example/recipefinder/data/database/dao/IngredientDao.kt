package com.example.recipefinder.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.recipefinder.data.database.entity.IngredientDB

@Dao
interface IngredientDao {

    @Query("SELECT COUNT(idIngredient) FROM IngredientDB")
    fun ingredientCount(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertIngredients(ingredients: List<IngredientDB>)

    @Query("SELECT * FROM IngredientDB")
    fun getIngredients(): List<IngredientDB>
}