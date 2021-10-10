package com.example.recipefinder.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.*
import com.example.use.RecipeUseCases
import com.orhanobut.logger.Logger
import kotlinx.coroutines.launch

class SecondaryViewModel(private val recipeUseCases: RecipeUseCases,filterType: String) : ViewModel() {

    private val _model = MutableLiveData<Event<SecondaryModel>>()
    val model: LiveData<Event<SecondaryModel>>
        get() = _model

    sealed class SecondaryModel {
        class AreaList(val countries: List<Country>) : SecondaryModel()
        class CategoryList(val categories: List<Category>) : SecondaryModel()
        class IngredientList(val ingredients: List<Ingredient>) : SecondaryModel()

        class FilteredRecipeList(val listOfRecipes: List<Recipe>) : SecondaryModel()
    }

    init {
        getListOfFilters(filterType)
    }


    private fun getListOfFilters(filterType: String) {
        viewModelScope.launch {
            when (filterType) {
                "Countries" -> {
                    when (val response = recipeUseCases.getListOfAreas()) {
                        is Either.Left -> {
                            Logger.d("error en la API: ${response.l}")
                        }
                        is Either.Right -> {
                            Logger.d("getListOfAreas prueba nombre: ${response.r[0]}")
                            _model.value = Event(SecondaryModel.AreaList(response.r))
                        }
                    }
                }
                "Ingredient" -> {
                    when (val response = recipeUseCases.getListOfIngredients()) {
                        is Either.Left -> {
                            Logger.d("error en la API: ${response.l}")
                        }
                        is Either.Right -> {
                            Logger.d("getListOfIngredients prueba nombre: ${response.r[0].strIngredient}")
                            Logger.d("id: ${response.r[0].idIngredient}")
                            _model.value = Event(SecondaryModel.IngredientList(response.r))
                        }
                    }
                }
                "Category" -> {
                    when (val response = recipeUseCases.getCategories()) {
                        is Either.Left -> {
                            Logger.d("error en la API: ${response.l}")
                        }
                        is Either.Right -> {
                            Logger.d("getCategories prueba nombre: ${response.r[0].strCategory}")
                            Logger.d("id: ${response.r[0].idCategory}")
                            _model.value = Event(SecondaryModel.CategoryList(response.r))
                        }
                    }
                }
            }
        }
    }

    fun filterByArea(country: String) {
        viewModelScope.launch {
            when (val response = recipeUseCases.filterByArea(country)) {
                is Either.Left -> {
                    Logger.d("error en la API: ${response.l}")
                }
                is Either.Right -> {
                    Logger.d("filterByAreas prueba nombre: ${response.r[0]}")
                    _model.value = Event(SecondaryModel.FilteredRecipeList(response.r))
                }
            }
        }
    }

    fun filterByIngredient(ingredient: String) {
        viewModelScope.launch {
            when (val response = recipeUseCases.filterByIngredient(ingredient)) {
                is Either.Left -> {
                    Logger.d("error en la API: ${response.l}")
                }
                is Either.Right -> {
                    Logger.d("filterByIngredient prueba nombre: ${response.r[0]}")
                    _model.value = Event(SecondaryModel.FilteredRecipeList(response.r))
                }
            }
        }
    }

    fun filterByCategory(category: String) {
        viewModelScope.launch {
            when (val response = recipeUseCases.filterByCategory(category)) {
                is Either.Left -> {
                    Logger.d("error en la API: ${response.l}")
                }
                is Either.Right -> {
                    Logger.d("filterByCategory prueba nombre: ${response.r[0]}")
                    _model.value = Event(SecondaryModel.FilteredRecipeList(response.r))
                }
            }
        }
    }
}