package com.example.recipefinder.ui

import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.*
import com.example.recipefinder.data.server.theMealDB.NETWORK_STATUS
import com.example.use.RecipeUseCases
import com.orhanobut.logger.Logger
import kotlinx.coroutines.launch

class SecondaryViewModel(private val recipeUseCases: RecipeUseCases) : ViewModel() {

    private val _model = MutableLiveData<Event<SecondaryModel>>()
    val model: LiveData<Event<SecondaryModel>>
        get() = _model

    sealed class SecondaryModel {
        class AreaList(val countries: List<Country>) : SecondaryModel()
        class CategoryList(val categories: List<Category>) : SecondaryModel()
        class IngredientList(val ingredients: List<Ingredient>) : SecondaryModel()

        class Network(val networkStatus: NETWORK_STATUS) : SecondaryModel()

        class FilteredRecipeList(val listOfRecipes: List<Recipe>) : SecondaryModel()
    }

    fun getListOfFilters(filterType: String) {
        _model.value = Event(SecondaryModel.Network(NETWORK_STATUS.LOADING))
        val handler = Handler()
        handler.postDelayed(
            {
                viewModelScope.launch {
                    when (filterType) {
                        "Countries" -> {
                            when (val response = recipeUseCases.getListOfAreas()) {
                                is Either.Left -> {
                                    Logger.d("error en la API: ${response.l}")
                                    _model.value =
                                        Event(SecondaryModel.Network(NETWORK_STATUS.ERROR))
                                }
                                is Either.Right -> {
                                    Logger.d("getListOfAreas: ${response.r}")
                                    _model.value =
                                        Event(SecondaryModel.Network(NETWORK_STATUS.DONE))
                                    _model.value = Event(SecondaryModel.AreaList(response.r))
                                }
                            }
                        }
                        "Ingredient" -> {
                            when (val response = recipeUseCases.getListOfIngredients()) {
                                is Either.Left -> {
                                    Logger.d("error en la API: ${response.l}")
                                    _model.value =
                                        Event(SecondaryModel.Network(NETWORK_STATUS.ERROR))
                                }
                                is Either.Right -> {
                                    Logger.d("getListOfIngredients prueba nombre: ${response.r[0].strIngredient}")
                                    Logger.d("id: ${response.r[0].idIngredient}")
                                    _model.value =
                                        Event(SecondaryModel.Network(NETWORK_STATUS.DONE))
                                    _model.value = Event(SecondaryModel.IngredientList(response.r))
                                }
                            }
                        }
                        "Category" -> {
                            when (val response = recipeUseCases.getListOfCategories()) {
                                is Either.Left -> {
                                    Logger.d("error en la API: ${response.l}")
                                    _model.value =
                                        Event(SecondaryModel.Network(NETWORK_STATUS.ERROR))
                                }
                                is Either.Right -> {
                                    Logger.d("getCategories prueba nombre: ${response.r[0].strCategory}")
                                    Logger.d("id: ${response.r[0].idCategory}")
                                    _model.value =
                                        Event(SecondaryModel.Network(NETWORK_STATUS.DONE))
                                    _model.value = Event(SecondaryModel.CategoryList(response.r))
                                }
                            }
                        }
                    }
                }
            },
            500
        )
    }

    fun filterByArea(country: String) {
        _model.value = Event(SecondaryModel.Network(NETWORK_STATUS.LOADING))
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
        _model.value = Event(SecondaryModel.Network(NETWORK_STATUS.LOADING))
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
        _model.value = Event(SecondaryModel.Network(NETWORK_STATUS.LOADING))
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