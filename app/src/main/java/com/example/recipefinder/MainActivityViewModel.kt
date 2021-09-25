package com.example.recipefinder

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.Either
import com.example.use.Recipe
import kotlinx.coroutines.launch

class MainActivityViewModel (recipe: Recipe): ViewModel() {
    init {
        Log.i("response1","response")
        viewModelScope.launch {
            //Prueba filter by area
            when(val response = recipe.filterByArea("American")){
                is Either.Left -> {
                    Log.i("response1","error en la API: ${response.l}")
                }
                is Either.Right -> {
                    Log.i("response1","Area prueba nombre: ${response.r[0].strMeal}")
                    Log.i("response1","id: ${response.r[0].idMeal}")
                }
            }

            //Prueba filter By Category
            when(val response = recipe.filterByCategory("Seafood")){
                is Either.Left -> {
                    Log.i("response1","error en la API: ${response.l}")
                }
                is Either.Right -> {
                    Log.i("response1","Category prueba nombre: ${response.r[0].strMeal}")
                    Log.i("response1","id: ${response.r[0].idMeal}")
                }
            }

            //Prueba flter by ingredient
            when(val response = recipe.filterByIngredient("chicken_breast")){
                is Either.Left -> {
                    Log.i("response1","error en la API: ${response.l}")
                }
                is Either.Right -> {
                    Log.i("response1","Ingredient prueba nombre: ${response.r[0].strMeal}")
                    Log.i("response1","id: ${response.r[0].idMeal}")
                }
            }

            //Prueba Ingredient
            when(val response = recipe.getListOfIngredients()){
                is Either.Left -> {
                    Log.i("response1","error en la API: ${response.l}")
                }
                is Either.Right -> {
                    Log.i("response1","getListOfIngredients prueba nombre: ${response.r[0].strIngredient}")
                    Log.i("response1","id: ${response.r[0].idIngredient}")
                }
            }

            //Prueba getCategories
            when(val response = recipe.getCategories()){
                is Either.Left -> {
                    Log.i("response1","error en la API: ${response.l}")
                }
                is Either.Right -> {
                    Log.i("response1","getCategories prueba nombre: ${response.r[0].strCategory}")
                    Log.i("response1","id: ${response.r[0].idCategory}")
                }
            }

            //Prueba getRandomMeal
            when(val response = recipe.getRandomMeal()){
                is Either.Left -> {
                    Log.i("response1","error en la API: ${response.l}")
                }
                is Either.Right -> {
                    Log.i("response1","getRandomMeal prueba nombre: ${response.r[0].strArea}")
                    Log.i("response1","id: ${response.r[0].strCategory}")
                }
            }

            //Prueba getListOfAreas
            when(val response = recipe.getListOfAreas()){
                is Either.Left -> {
                    Log.i("response1","error en la API: ${response.l}")
                }
                is Either.Right -> {
                    Log.i("response1","getListOfAreas prueba nombre: ${response.r[0]}")
                }
            }

            //Prueba getByID
            when(val response = recipe.getByID("52772")){
                is Either.Left -> {
                    Log.i("response1","error en la API: ${response.l}")
                }
                is Either.Right -> {
                    Log.i("response1","getByID prueba nombre: ${response.r[0].strArea}")
                    Log.i("response1","id: ${response.r[0].strIngredient1}")
                }
            }

            //Prueba getByName
            when(val response = recipe.getByName("Arrabiata")){
                is Either.Left -> {
                    Log.i("response1","error en la API: ${response.l}")
                }
                is Either.Right -> {
                    Log.i("response1","getByName prueba nombre: ${response.r[0].strIngredient1}")
                    Log.i("response1","id: ${response.r[0].idMeal}")
                }
            }

            //Prueba Ingredient
            when(val response = recipe.getByFirstLetter("a")){
                is Either.Left -> {
                    Log.i("response1","error en la API: ${response.l}")
                }
                is Either.Right -> {
                    Log.i("response1","getListOfIngredients prueba nombre: ${response.r[0].strIngredient1}")
                    Log.i("response1","id: ${response.r[0].idMeal}")
                }
            }
        }
    }
}