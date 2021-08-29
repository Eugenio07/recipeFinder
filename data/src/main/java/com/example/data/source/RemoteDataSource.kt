package com.example.data.source

import com.example.domain.Category
import com.example.domain.Ingredient
import com.example.domain.Recipe
import com.example.domain.RecipeName

interface RemoteDataSource {

    suspend fun getByName(mealName: String): List<Recipe>

    suspend fun getByFirstLetter(letter: String): List<Recipe>

    suspend fun getByID(mealID: String): List<Recipe>

    suspend fun getRandomMeal(): List<Recipe>

    suspend fun getCategories(): List<Category>

    suspend fun getListOfAreas(): List<String>

    suspend fun getListOfIngredients(): List<Ingredient>

    suspend fun filterByIngredient(ingredient: String): List<RecipeName>

    suspend fun filterByCategory(category: String): List<RecipeName>

    suspend fun filterByArea(area: String): List<RecipeName>

}