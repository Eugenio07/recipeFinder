package com.example.data.repository

import com.example.data.source.LocalDataSource
import com.example.domain.Recipe

class RecipeRepository(private val localDataSource: LocalDataSource) {

    suspend fun getFavoritesRecipes(): List<Recipe>{
    return localDataSource.getFavorites()
    }

    suspend fun saveFavoriteRecipe(recipe: Recipe){
        localDataSource.addToFavorite(recipe)
    }
}