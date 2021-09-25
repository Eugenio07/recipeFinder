package com.example.recipefinder

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.Either
import com.example.use.Recipe
import com.orhanobut.logger.Logger
import kotlinx.coroutines.launch

class MainActivityViewModel (recipe: Recipe): ViewModel() {
    init {
        Logger.d("response")
        viewModelScope.launch {
            //Prueba filter by area
            when(val response = recipe.filterByArea("American")){
                is Either.Left -> {
                    Logger.d("error en la API: ${response.l}")
                }
                is Either.Right -> {
                    Logger.d("Area prueba nombre: ${response.r[0].strMeal}")
                    Logger.d("id: ${response.r[0].idMeal}")
                }
            }

            //Prueba filter By Category
            when(val response = recipe.filterByCategory("Seafood")){
                is Either.Left -> {
                    Logger.d("error en la API: ${response.l}")
                }
                is Either.Right -> {
                    Logger.d("Category prueba nombre: ${response.r[0].strMeal}")
                    Logger.d("id: ${response.r[0].idMeal}")
                }
            }

            //Prueba flter by ingredient
            when(val response = recipe.filterByIngredient("chicken_breast")){
                is Either.Left -> {
                    Logger.d("error en la API: ${response.l}")
                }
                is Either.Right -> {
                    Logger.d("Ingredient prueba nombre: ${response.r[0].strMeal}")
                    Logger.d("id: ${response.r[0].idMeal}")
                }
            }

            //Prueba Ingredient
            when(val response = recipe.getListOfIngredients()){
                is Either.Left -> {
                    Logger.d("error en la API: ${response.l}")
                }
                is Either.Right -> {
                    Logger.d("getListOfIngredients prueba nombre: ${response.r[0].strIngredient}")
                    Logger.d("id: ${response.r[0].idIngredient}")
                }
            }

            //Prueba getCategories
            when(val response = recipe.getCategories()){
                is Either.Left -> {
                    Logger.d("error en la API: ${response.l}")
                }
                is Either.Right -> {
                    Logger.d("getCategories prueba nombre: ${response.r[0].strCategory}")
                    Logger.d("id: ${response.r[0].idCategory}")
                }
            }

            //Prueba getRandomMeal
            when(val response = recipe.getRandomMeal()){
                is Either.Left -> {
                    Logger.d("error en la API: ${response.l}")
                }
                is Either.Right -> {
                    Logger.d("getRandomMeal prueba nombre: ${response.r[0].strArea}")
                    Logger.d("id: ${response.r[0].strCategory}")
                }
            }

            //Prueba getListOfAreas
            when(val response = recipe.getListOfAreas()){
                is Either.Left -> {
                    Logger.d("error en la API: ${response.l}")
                }
                is Either.Right -> {
                    Logger.d("getListOfAreas prueba nombre: ${response.r[0]}")
                }
            }

            //Prueba getByID
            when(val response = recipe.getByID("52772")){
                is Either.Left -> {
                    Logger.d("error en la API: ${response.l}")
                }
                is Either.Right -> {
                    Logger.d("getByID prueba nombre: ${response.r[0].strArea}")
                    Logger.d("id: ${response.r[0].strIngredient1}")
                }
            }

            //Prueba getByName
            when(val response = recipe.getByName("Arrabiata")){
                is Either.Left -> {
                    Logger.d("error en la API: ${response.l}")
                }
                is Either.Right -> {
                    Logger.d("getByName prueba nombre: ${response.r[0].strIngredient1}")
                    Logger.d("id: ${response.r[0].idMeal}")
                }
            }

            //Prueba Ingredient
            when(val response = recipe.getByFirstLetter("a")){
                is Either.Left -> {
                    Logger.d("error en la API: ${response.l}")
                }
                is Either.Right -> {
                    Logger.d("getListOfIngredients prueba nombre: ${response.r[0].strIngredient1}")
                    Logger.d("id: ${response.r[0].idMeal}")
                }
            }
        }
    }
}