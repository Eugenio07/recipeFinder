package com.example.recipefinder.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.recipefinder.data.database.entity.CategoryDB

@Dao
interface CategoryDao {

    @Query("SELECT COUNT(idCategory) FROM CategoryDB")
    fun categoryCount(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertCategories(categories: List<CategoryDB>)

    @Query("SELECT * FROM CategoryDB")
    fun getCategories(): List<CategoryDB>
}