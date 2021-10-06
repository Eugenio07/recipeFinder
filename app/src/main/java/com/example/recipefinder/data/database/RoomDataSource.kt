package com.example.recipefinder.data.database

import com.example.data.source.LocalDataSource
import com.example.domain.Recipe
import com.example.recipefinder.data.toRecipe
import com.example.recipefinder.data.toRecipeDB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RoomDataSource(db: RecipeDataBase) : LocalDataSource {

    private val recipeDao = db.recipeDao()

    override suspend fun isEmpty(): Boolean =
        withContext(Dispatchers.IO) { recipeDao.recipeCount() <= 0 }

    override suspend fun findByID(id: String): Recipe? =
        withContext(Dispatchers.IO) { recipeDao.findById(id)?.let{it.toRecipe()} }

    override suspend fun addToFavorite(recipe: Recipe) {
        withContext(Dispatchers.IO) { recipeDao.insertRecipe(recipe.toRecipeDB()) }
    }

    override suspend fun deleteFromFavorite(recipe: Recipe) {
        withContext(Dispatchers.IO) { recipeDao.deleteRecipe(recipe.toRecipeDB()) }
    }

    override suspend fun getFavorites(): List<Recipe> =
        withContext(Dispatchers.IO) { recipeDao.getAll().map { it.toRecipe() } }

}