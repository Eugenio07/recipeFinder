package com.example.data.source

import com.example.domain.Recipe

interface LocalDataSource {
    suspend fun isEmpty(): Boolean
    suspend fun findByID(id:String):Recipe?
    suspend fun addToFavorite(recipe: Recipe)
    suspend fun deleteFromFavorite(recipe: Recipe)
    suspend fun getFavorites(): List<Recipe>
}