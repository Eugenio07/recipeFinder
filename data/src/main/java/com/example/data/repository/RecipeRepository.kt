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

    suspend fun deleteFavoriteRecipe(recipe: Recipe) =
        localDataSource.deleteFromFavorite(recipe)

    suspend fun findFavoriteByID(id: String): Recipe? =
        localDataSource.findByID(id)

    suspend fun getRecipeById(id: String): Either<String, List<Recipe>> =
        remoteDataSource.getByID(id)

    suspend fun getRecipeByName(name: String): Either<String, List<Recipe>> =
        remoteDataSource.getByName(name)

    suspend fun getRandomMeal(): Either<String, List<Recipe>> =
        remoteDataSource.getRandomMeal()

    suspend fun getByFirstLetter(letter: String): Either<String, List<Recipe>> =
        remoteDataSource.getByFirstLetter(letter)

    suspend fun getListOfCategories(): Either<String, List<Category>> {
        if (localDataSource.categoryListIsEmpty()) {
            when (val category = remoteDataSource.getListOfCategories()) {
                is Either.Left -> return Either.Left(category.l)
                is Either.Right -> localDataSource.saveCategoryList(category.r)
            }
        }
        return Either.Right(localDataSource.getCategoryList())
    }

    suspend fun getListOfAreas(): Either<String, List<Country>> {
        if (localDataSource.countryListIsEmpty()) {
            when (val countries = remoteDataSource.getListOfAreas()) {
                is Either.Left -> return Either.Left(countries.l)
                is Either.Right -> localDataSource.saveCountryList(countries.r)
            }
        }
        return Either.Right(localDataSource.getCountryList())
    }

    suspend fun getListOfIngredients(): Either<String, List<Ingredient>> {
        if (localDataSource.ingredientListIsEmpty()) {
            when (val ingredient = remoteDataSource.getListOfIngredients()) {
                is Either.Left -> return Either.Left(ingredient.l)
                is Either.Right -> localDataSource.saveIngredientList(ingredient.r)
            }
        }
        return Either.Right(localDataSource.getIngredientList())
    }

    suspend fun filterByIngredient(ingredient: String): Either<String, List<Recipe>> =
        remoteDataSource.filterByIngredient(ingredient)

    suspend fun filterByCategory(category: String): Either<String, List<Recipe>> =
        remoteDataSource.filterByCategory(category)

    suspend fun filterByArea(area: String): Either<String, List<Recipe>> =
        remoteDataSource.filterByArea(area)
}