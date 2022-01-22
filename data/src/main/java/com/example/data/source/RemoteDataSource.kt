package com.example.data.source

import com.example.domain.*

interface RemoteDataSourceTest: RemoteDataSource

interface RemoteDataSource {

    suspend fun getByName(mealName: String): Either<String, List<Recipe>>

    suspend fun getByFirstLetter(letter: String): Either<String, List<Recipe>>

    suspend fun getByID(mealID: String): Either<String, List<Recipe>>

    suspend fun getRandomMeal(): Either<String, List<Recipe>>

    suspend fun getListOfCategories(): Either<String, List<Category>>

    suspend fun getListOfAreas(): Either<String, List<String>>

    suspend fun getListOfIngredients(): Either<String, List<Ingredient>>

    suspend fun filterByIngredient(ingredient: String): Either<String, List<Recipe>>

    suspend fun filterByCategory(category: String): Either<String, List<Recipe>>

    suspend fun filterByArea(area: String): Either<String,List<Recipe>>

}