package com.example.recipefinder.data.server.theMealDB

import com.example.data.source.RemoteDataSource
import com.example.domain.Category
import com.example.domain.Ingredient
import com.example.domain.Recipe
import com.example.domain.RecipeName
import com.example.recipefinder.data.toDomainCategory
import com.example.recipefinder.data.toDomainIngredient
import com.example.recipefinder.data.toRecipe
import com.example.recipefinder.data.toRecipeName

class TheMealDBDataSource: RemoteDataSource {
    override suspend fun getByName(mealName: String): List<Recipe> =
        TheMealApi.retrofitService.getByName(mealName).meals.map { it.toRecipe() }

    override suspend fun getByFirstLetter(letter: String): List<Recipe> =
        TheMealApi.retrofitService.getByFirstLetter(letter).meals.map { it.toRecipe() }

    override suspend fun getByID(mealID: String): List<Recipe> =
        TheMealApi.retrofitService.getByID(mealID).meals.map { it.toRecipe() }

    override suspend fun getRandomMeal(): List<Recipe> =
        TheMealApi.retrofitService.getRandomMeal().meals.map { it.toRecipe() }

    override suspend fun getCategories(): List<Category> =
        TheMealApi.retrofitService.getCategories().categories.map { it.toDomainCategory() }

    override suspend fun getListOfAreas(): List<String> =
        TheMealApi.retrofitService.getListOfAreas().meals.map { it.toString() }

    override suspend fun getListOfIngredients(): List<Ingredient> =
        TheMealApi.retrofitService.getListOfIngredients().meals.map { it.toDomainIngredient() }

    override suspend fun filterByIngredient(ingredient: String): List<RecipeName> =
        TheMealApi.retrofitService.filterByIngredient(ingredient).meals.map { it.toRecipeName() }

    override suspend fun filterByCategory(category: String): List<RecipeName> =
        TheMealApi.retrofitService.filterByCategory(category).meals.map { it.toRecipeName() }

    override suspend fun filterByArea(area: String): List<RecipeName> =
        TheMealApi.retrofitService.filterByArea(area).meals.map { it.toRecipeName() }
}