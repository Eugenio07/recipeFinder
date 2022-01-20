package com.example.data.source

import com.example.domain.Category
import com.example.domain.Country
import com.example.domain.Ingredient
import com.example.domain.Recipe
interface LocalDataSourceTest: LocalDataSource

interface LocalDataSource {
    suspend fun recipeListIsEmpty(): Boolean
    suspend fun findByID(id:String):Recipe?
    suspend fun addToFavorite(recipe: Recipe)
    suspend fun deleteFromFavorite(recipe: Recipe)
    suspend fun getFavorites(): List<Recipe>

    suspend fun countryListIsEmpty(): Boolean
    suspend fun recipeCountryListIsEmpty(): Boolean
    suspend fun getCountryList(): List<Country>
    suspend fun saveCountryList(countries: List<Country>)
    suspend fun updateCountryList(countries: List<Country>)
    suspend fun getRecipeCountryList(): List<Country>
    suspend fun getCountryListByDemonym(demonyms: List<String>): List<Country>
    suspend fun isInRecipeList(country: String): Boolean
    suspend fun getCountryByCode(country: String): Country

    suspend fun categoryListIsEmpty(): Boolean
    suspend fun getCategoryList(): List<Category>
    suspend fun saveCategoryList(category: List<Category>)


    suspend fun ingredientListIsEmpty(): Boolean
    suspend fun getIngredientList(): List<Ingredient>
    suspend fun saveIngredientList(ingredient: List<Ingredient>)

//    suspend fun restCountryListIsEmpty(): Boolean
//    suspend fun getRestCountryList(): List<Country>
//    suspend fun saveRestCountryList(countries: List<Country>)
}