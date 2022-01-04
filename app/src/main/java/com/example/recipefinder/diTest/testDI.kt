package com.example.recipefinder

import com.example.data.repository.PermissionCheck
import com.example.data.repository.PermissionCheckTest
import com.example.data.source.*
import com.example.domain.*
import com.example.recipefinder.data.server.theMealDB.TheMealApi
import com.example.recipefinder.data.toRecipe
import mockedDomain.mockedRecipe

private val recipeParcelable = RecipeParcelable()
private var fakeListOfRecipes = mutableListOf(recipeParcelable.toRecipe())

class FakeLocalDataSource : LocalDataSourceTest {


    override suspend fun recipeListIsEmpty(): Boolean {
        return fakeListOfRecipes.isEmpty()
    }

    override suspend fun findByID(id: String): Recipe? =
        if (id == mockedRecipe.idMeal) mockedRecipe else null


    override suspend fun addToFavorite(recipe: Recipe) {
        fakeListOfRecipes.add(recipe)
    }

    override suspend fun deleteFromFavorite(recipe: Recipe) {
        fakeListOfRecipes.remove(recipe)
    }

    override suspend fun getFavorites(): List<Recipe> = fakeListOfRecipes

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

class FakeRemoteDataSource : RemoteDataSourceTest {
    override suspend fun getByName(mealName: String): Either<String, List<Recipe>> =
        if (mealName == "Arepa") {
            Either.Right(fakeListOfRecipes)
        } else {
            Either.Left("Connection failure")
        }


    override suspend fun getByFirstLetter(letter: String): Either<String, List<Recipe>> {
        TODO("Not yet implemented")
    }

    override suspend fun getByID(mealID: String): Either<String, List<Recipe>> {
        TODO("Not yet implemented")
    }

    override suspend fun getRandomMeal(): Either<String, List<Recipe>> {
        return Either.Right(fakeListOfRecipes)
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

class FakeCountryDataSource : CountriesDataSourceTest {
    override suspend fun getAllCountries(): Either<String, List<Country>> {
        TODO("Not yet implemented")
    }

    override suspend fun getLocation(): String? {
        TODO("Not yet implemented")
    }
}

class FakePermissionChecker : PermissionCheckTest {
    override suspend fun request(permission: List<PermissionCheck.Permission>): Pair<Boolean, Boolean> {
        TODO("Not yet implemented")
    }
}