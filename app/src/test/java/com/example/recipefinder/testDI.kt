package com.example.recipefinder

import com.example.data.repository.PermissionCheck
import com.example.data.source.CountriesDataSource
import com.example.data.source.LocalDataSource
import com.example.data.source.RemoteDataSource
import com.example.domain.*
import mockedDomain.mockedRecipe

class FakeLocalDataSource: LocalDataSource{
    override suspend fun recipeListIsEmpty(): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun findByID(id: String): Recipe? = if(id == mockedRecipe.idMeal) mockedRecipe else null


    override suspend fun addToFavorite(recipe: Recipe) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteFromFavorite(recipe: Recipe) {
        TODO("Not yet implemented")
    }

    override suspend fun getFavorites(): List<Recipe> {
        TODO("Not yet implemented")
    }

    override suspend fun countryListIsEmpty(): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun recipeCountryListIsEmpty(): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun getCountryList(): List<Country> {
        TODO("Not yet implemented")
    }

    override suspend fun saveCountryList(countries: List<Country>) {
        TODO("Not yet implemented")
    }

    override suspend fun updateCountryList(countries: List<Country>) {
        TODO("Not yet implemented")
    }

    override suspend fun getRecipeCountryList(): List<Country> {
        TODO("Not yet implemented")
    }

    override suspend fun getCountryListByDemonym(demonyms: List<String>): List<Country> {
        TODO("Not yet implemented")
    }

    override suspend fun isInRecipeList(country: String): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun categoryListIsEmpty(): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun getCategoryList(): List<Category> {
        TODO("Not yet implemented")
    }

    override suspend fun saveCategoryList(category: List<Category>) {
        TODO("Not yet implemented")
    }

    override suspend fun ingredientListIsEmpty(): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun getIngredientList(): List<Ingredient> {
        TODO("Not yet implemented")
    }

    override suspend fun saveIngredientList(ingredient: List<Ingredient>) {
        TODO("Not yet implemented")
    }

}

class FakeRemoteDataSource: RemoteDataSource {
    override suspend fun getByName(mealName: String): Either<String, List<Recipe>> {
        TODO("Not yet implemented")
    }

    override suspend fun getByFirstLetter(letter: String): Either<String, List<Recipe>> {
        TODO("Not yet implemented")
    }

    override suspend fun getByID(mealID: String): Either<String, List<Recipe>> {
        TODO("Not yet implemented")
    }

    override suspend fun getRandomMeal(): Either<String, List<Recipe>> {
        TODO("Not yet implemented")
    }

    override suspend fun getListOfCategories(): Either<String, List<Category>> {
        TODO("Not yet implemented")
    }

    override suspend fun getListOfAreas(): Either<String, List<String>> {
        TODO("Not yet implemented")
    }

    override suspend fun getListOfIngredients(): Either<String, List<Ingredient>> {
        TODO("Not yet implemented")
    }

    override suspend fun filterByIngredient(ingredient: String): Either<String, List<Recipe>> {
        TODO("Not yet implemented")
    }

    override suspend fun filterByCategory(category: String): Either<String, List<Recipe>> {
        TODO("Not yet implemented")
    }

    override suspend fun filterByArea(area: String): Either<String, List<Recipe>> {
        TODO("Not yet implemented")
    }
}

class FakeCountryDataSource: CountriesDataSource {
    override suspend fun getAllCountries(): Either<String, List<Country>> {
        TODO("Not yet implemented")
    }

    override suspend fun getLocation(): String? {
        TODO("Not yet implemented")
    }
}

class FakePermissionChecker: PermissionCheck {
    override suspend fun request(permission: List<PermissionCheck.Permission>): Pair<Boolean, Boolean> {
        TODO("Not yet implemented")
    }
}