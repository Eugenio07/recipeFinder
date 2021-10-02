package com.example.recipefinder.data.server.theMealDB

import com.example.data.source.RemoteDataSource
import com.example.domain.*
import com.example.recipefinder.data.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class TheMealDBDataSource: RemoteDataSource {
    override suspend fun getByName(mealName: String): Either<String, List<Recipe>> {
        return withContext(Dispatchers.IO) {
            try {
                Either.Right(TheMealApi.retrofitService.getByName(mealName).meals.map { it.toRecipe() })
            } catch (e: HttpException) {
                Either.Left("Connection failure")
            } catch (e: Exception) {
                Either.Left(e.message ?: "Connection failure")
            }
        }
    }

    override suspend fun getByFirstLetter(letter: String): Either<String, List<Recipe>> {
        return withContext(Dispatchers.IO) {
            try {
                Either.Right(TheMealApi.retrofitService.getByFirstLetter(letter).meals.map { it.toRecipe() })
            } catch (e: HttpException) {
                Either.Left("Connection failure")
            } catch (e: Exception) {
                Either.Left(e.message ?: "Connection failure")
            }
        }
    }

    override suspend fun getByID(mealID: String): Either<String, List<Recipe>> {
        return withContext(Dispatchers.IO) {
            try {
                Either.Right(TheMealApi.retrofitService.getByID(mealID).meals.map { it.toRecipe() })
            } catch (e: HttpException) {
                Either.Left("Connection failure")
            } catch (e: Exception) {
                Either.Left(e.message ?: "Connection failure")
            }
        }
    }

    override suspend fun getRandomMeal(): Either<String, List<Recipe>> {
        return withContext(Dispatchers.IO) {
            try {
                Either.Right(TheMealApi.retrofitService.getRandomMeal().meals.map { it.toRecipe() })
            } catch (e: HttpException) {
                Either.Left("Connection failure")
            } catch (e: Exception) {
                Either.Left(e.message ?: "Connection failure")
            }
        }
    }

    override suspend fun getCategories(): Either<String, List<Category>> {
        return withContext(Dispatchers.IO){
            try {
                Either.Right(TheMealApi.retrofitService.getCategories().categories.map { it.toDomainCategory() })
            }catch (e: HttpException){
                Either.Left("Connection failure")
            }catch (e: Exception){
                Either.Left(e.message?:"Connection failure")
            }
        }
    }

    override suspend fun getListOfAreas(): Either<String, List<Country>> {
        return withContext(Dispatchers.IO){
            try {
                Either.Right(TheMealApi.retrofitService.getListOfAreas().meals.map { it.toDomainArea() })
            }catch (e: HttpException){
                Either.Left("Connection failure")
            }catch (e: Exception){
                Either.Left(e.message?:"Connection failure")
            }
        }
    }

    override suspend fun getListOfIngredients(): Either<String, List<Ingredient>> {
        return withContext(Dispatchers.IO){
            try {
                Either.Right(TheMealApi.retrofitService.getListOfIngredients().meals.map { it.toDomainIngredient() })
            }catch (e: HttpException){
                Either.Left("Connection failure")
            }catch (e: Exception){
                Either.Left(e.message?:"Connection failure")
            }
        }
    }

    override suspend fun filterByIngredient(ingredient: String): Either<String, List<Recipe>> {
        return withContext(Dispatchers.IO){
            try {
                Either.Right(TheMealApi.retrofitService.filterByIngredient(ingredient).meals.map { it.toFilterRecipe() })
            }catch (e: HttpException){
                Either.Left("Connection failure")
            }catch (e: Exception){
                Either.Left(e.message?:"Connection failure")
            }
        }
    }

    override suspend fun filterByCategory(category: String): Either<String, List<Recipe>> {
        return withContext(Dispatchers.IO){
            try {
                Either.Right(TheMealApi.retrofitService.filterByCategory(category).meals.map { it.toFilterRecipe() })
            }catch (e: HttpException){
                Either.Left("Connection failure")
            }catch (e: Exception){
                Either.Left(e.message?:"Connection failure")
            }
        }
    }

    override suspend fun filterByArea(area: String): Either<String, List<Recipe>> {
        return withContext(Dispatchers.IO){
            try {
                Either.Right(TheMealApi.retrofitService.filterByArea(area).meals.map { it.toFilterRecipe() })
            }catch (e: HttpException){
                Either.Left("Connection failure")
            }catch (e: Exception){
                Either.Left(e.message?:"Connection failure")
            }
        }
    }
}