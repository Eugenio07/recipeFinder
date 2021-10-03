package com.example.recipefinder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.Either
import com.example.use.RecipeUseCases
import com.orhanobut.logger.Logger
import kotlinx.coroutines.launch

class MainActivityViewModel (recipeUseCases: RecipeUseCases): ViewModel() {

//    init {
//        Logger.d("response")
//        viewModelScope.launch {
//            //Prueba filter by area
//            when(val response = recipeUseCases.filterByArea("American")){
//                is Either.Left -> {
//                    Logger.d("error en la API: ${response.l}")
//                }
//                is Either.Right -> {
//                    Logger.d("Area prueba nombre: ${response.r[0].strMeal}")
//                    Logger.d("id: ${response.r[0].idMeal}")
//                }
//            }
//
//            //Prueba filter By Category
//            when(val response = recipeUseCases.filterByCategory("Seafood")){
//                is Either.Left -> {
//                    Logger.d("error en la API: ${response.l}")
//                }
//                is Either.Right -> {
//                    Logger.d("Category prueba nombre: ${response.r[0].strMeal}")
//                    Logger.d("id: ${response.r[0].idMeal}")
//                }
//            }
//
//            //Prueba flter by ingredient
//            when(val response = recipeUseCases.filterByIngredient("chicken_breast")){
//                is Either.Left -> {
//                    Logger.d("error en la API: ${response.l}")
//                }
//                is Either.Right -> {
//                    Logger.d("Ingredient prueba nombre: ${response.r[0].strMeal}")
//                    Logger.d("id: ${response.r[0].idMeal}")
//                }
//            }
//
//            //Prueba Ingredient
//            when(val response = recipeUseCases.getListOfIngredients()){
//                is Either.Left -> {
//                    Logger.d("error en la API: ${response.l}")
//                }
//                is Either.Right -> {
//                    Logger.d("getListOfIngredients prueba nombre: ${response.r[0].strIngredient}")
//                    Logger.d("id: ${response.r[0].idIngredient}")
//                }
//            }
//
//            //Prueba getCategories
//            when(val response = recipeUseCases.getCategories()){
//                is Either.Left -> {
//                    Logger.d("error en la API: ${response.l}")
//                }
//                is Either.Right -> {
//                    Logger.d("getCategories prueba nombre: ${response.r[0].strCategory}")
//                    Logger.d("id: ${response.r[0].idCategory}")
//                }
//            }
//
//            //Prueba getRandomMeal
//            when(val response = recipeUseCases.getRandomMeal()){
//                is Either.Left -> {
//                    Logger.d("error en la API: ${response.l}")
//                }
//                is Either.Right -> {
//                    Logger.d("getRandomMeal prueba nombre: ${response.r[0].strArea}")
//                    Logger.d("id: ${response.r[0].strCategory}")
//                }
//            }
//
//            //Prueba getListOfAreas
//            when(val response = recipeUseCases.getListOfAreas()){
//                is Either.Left -> {
//                    Logger.d("error en la API: ${response.l}")
//                }
//                is Either.Right -> {
//                    Logger.d("getListOfAreas prueba nombre: ${response.r[0]}")
//                }
//            }
//
//            //Prueba getByID
//            when(val response = recipeUseCases.getByID("52772")){
//                is Either.Left -> {
//                    Logger.d("error en la API: ${response.l}")
//                }
//                is Either.Right -> {
//                    Logger.d("getByID prueba nombre: ${response.r[0].strArea}")
//                    Logger.d("id: ${response.r[0].strIngredient1}")
//                }
//            }
//
//            //Prueba getByName
//            when(val response = recipeUseCases.getByName("Arrabiata")){
//                is Either.Left -> {
//                    Logger.d("error en la API: ${response.l}")
//                }
//                is Either.Right -> {
//                    Logger.d("getByName prueba nombre: ${response.r[0].strIngredient1}")
//                    Logger.d("id: ${response.r[0].idMeal}")
//                }
//            }
//
//            //Prueba Ingredient
//            when(val response = recipeUseCases.getByFirstLetter("a")){
//                is Either.Left -> {
//                    Logger.d("error en la API: ${response.l}")
//                }
//                is Either.Right -> {
//                    Logger.d("getListOfIngredients prueba nombre: ${response.r[0].strIngredient1}")
//                    Logger.d("id: ${response.r[0].idMeal}")
//                }
//            }
//        }
//    }
}