package com.example.data.repository

import com.example.data.source.LocalDataSource
import com.example.data.source.RemoteDataSource
import com.example.domain.Category
import com.example.domain.Ingredient
import com.example.domain.Recipe
import com.example.domain.RecipeName

class RecipeRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) {

    suspend fun getFavoritesRecipes(): List<Recipe> =
        localDataSource.getFavorites()

    suspend fun saveFavoriteRecipe(recipe: Recipe) =
        localDataSource.addToFavorite(recipe)

    suspend fun getRecipeById(id: String): List<Recipe> =
        remoteDataSource.getByID(id)

    suspend fun getRecipeByName(name: String): List<Recipe> =
        remoteDataSource.getByName(name)

    suspend fun getRandomMeal(): List<Recipe> =
        remoteDataSource.getRandomMeal()

    suspend fun getByFirstLetter(letter: String): List<Recipe> =
        remoteDataSource.getByFirstLetter(letter)

    suspend fun getCategories(): List<Category> =
        remoteDataSource.getCategories()

    suspend fun getListOfAreas(): List<String> =
        remoteDataSource.getListOfAreas()

    suspend fun getListOfIngredients(): List<Ingredient> =
        remoteDataSource.getListOfIngredients()

    suspend fun filterByIngredient(ingredient: String): List<RecipeName> =
        remoteDataSource.filterByIngredient(ingredient)

    suspend fun filterByCategory(category: String): List<RecipeName> =
        remoteDataSource.filterByCategory(category)

    suspend fun filterByArea(area: String): List<RecipeName> =
        remoteDataSource.filterByArea(area)
}