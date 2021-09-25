package com.example.data.repository

import com.example.data.source.LocalDataSource
import com.example.data.source.RemoteDataSource
import com.example.domain.*

class RecipeRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) {

    suspend fun getFavoritesRecipes(): List<Recipe> =
        localDataSource.getFavorites()

    suspend fun saveFavoriteRecipe(recipe: Recipe) =
        localDataSource.addToFavorite(recipe)

    suspend fun getRecipeById(id: String): Either<String, List<Recipe>> =
        remoteDataSource.getByID(id)

    suspend fun getRecipeByName(name: String): Either<String, List<Recipe>> =
        remoteDataSource.getByName(name)

    suspend fun getRandomMeal(): Either<String, List<Recipe>> =
        remoteDataSource.getRandomMeal()

    suspend fun getByFirstLetter(letter: String): Either<String, List<Recipe>> =
        remoteDataSource.getByFirstLetter(letter)

    suspend fun getCategories(): Either<String, List<Category>> =
        remoteDataSource.getCategories()

    suspend fun getListOfAreas(): Either<String, List<String>> =
        remoteDataSource.getListOfAreas()

    suspend fun getListOfIngredients(): Either<String, List<Ingredient>> =
        remoteDataSource.getListOfIngredients()

    suspend fun filterByIngredient(ingredient: String): Either<String, List<RecipeName>> =
        remoteDataSource.filterByIngredient(ingredient)

    suspend fun filterByCategory(category: String): Either<String, List<RecipeName>> =
        remoteDataSource.filterByCategory(category)

    suspend fun filterByArea(area: String): Either<String, List<RecipeName>> =
        remoteDataSource.filterByArea(area)
}