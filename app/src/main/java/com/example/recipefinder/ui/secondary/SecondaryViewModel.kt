package com.example.recipefinder.ui.secondary

import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.*
import com.example.recipefinder.ScopedViewModel
import com.example.recipefinder.data.server.theMealDB.NETWORK_STATUS
import com.example.use.CountryUseCases
import com.example.use.RecipeUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SecondaryViewModel @Inject constructor(
    private val recipeUseCases: RecipeUseCases,
    private val countryUseCases: CountryUseCases,
    uiDispatcher: CoroutineDispatcher
) : ScopedViewModel(uiDispatcher) {

    private val _model = MutableLiveData<Event<SecondaryModel>>()
    val model: LiveData<Event<SecondaryModel>>
        get() = _model

    private lateinit var country: Country

    init {
        initScope()
    }

    sealed class SecondaryModel {
        data class AreaList(val countries: List<Country>, val country: Country) : SecondaryModel()
        data class CategoryList(val categories: List<Category>) : SecondaryModel()
        data class IngredientList(val ingredients: List<Ingredient>) : SecondaryModel()
        data class Network(val networkStatus: NETWORK_STATUS) : SecondaryModel()
        data class FilteredRecipeList(val listOfRecipes: List<Recipe>) : SecondaryModel()
    }

    fun getListOfFilters(filterType: String) {
        _model.value = Event(SecondaryModel.Network(NETWORK_STATUS.LOADING))
        launch {
            when (filterType) {
                "Countries" -> {
                    when (val response = recipeUseCases.getListOfAreas()) {
                        is Either.Left -> {
                            _model.value =
                                Event(SecondaryModel.Network(NETWORK_STATUS.ERROR))
                        }
                        is Either.Right -> {
                            val location = countryUseCases.getLocation()
                            country =
                                if (location != "Unknown") countryUseCases.getCountryByCode(
                                    location
                                ) else
                                    Country("Unknown", "Unknown", "Unknown", null, false)
                            _model.value =
                                Event(SecondaryModel.Network(NETWORK_STATUS.DONE))
                            _model.value =
                                Event(SecondaryModel.AreaList(response.r, country))
                        }
                    }
                }
                "Ingredients" -> {
                    when (val response = recipeUseCases.getListOfIngredients()) {
                        is Either.Left -> {
                            _model.value =
                                Event(SecondaryModel.Network(NETWORK_STATUS.ERROR))
                        }
                        is Either.Right -> {
                            _model.value =
                                Event(SecondaryModel.Network(NETWORK_STATUS.DONE))
                            _model.value = Event(SecondaryModel.IngredientList(response.r))
                        }
                    }
                }
                "Categories" -> {
                    when (val response = recipeUseCases.getListOfCategories()) {
                        is Either.Left -> {
                            _model.value =
                                Event(SecondaryModel.Network(NETWORK_STATUS.ERROR))
                        }
                        is Either.Right -> {
                            _model.value =
                                Event(SecondaryModel.Network(NETWORK_STATUS.DONE))
                            _model.value = Event(SecondaryModel.CategoryList(response.r))
                        }
                    }
                }
            }
        }
    }

    fun myCountryRecipes() {
        filterByArea(country.demonym!!)
    }

    fun filterByArea(country: String) {
        _model.value = Event(SecondaryModel.Network(NETWORK_STATUS.LOADING))
        launch {
            when (val response = recipeUseCases.filterByArea(country)) {
                is Either.Left -> {
                }
                is Either.Right -> {
                    _model.value = Event(SecondaryModel.FilteredRecipeList(response.r))
                }
            }
        }
    }

    fun filterByIngredient(ingredient: String) {
        _model.value = Event(SecondaryModel.Network(NETWORK_STATUS.LOADING))
        launch {
            when (val response = recipeUseCases.filterByIngredient(ingredient)) {
                is Either.Left -> {
                }
                is Either.Right -> {
                    _model.value = Event(SecondaryModel.FilteredRecipeList(response.r))
                }
            }
        }
    }

    fun filterByCategory(category: String) {
        _model.value = Event(SecondaryModel.Network(NETWORK_STATUS.LOADING))
        launch {
            when (val response = recipeUseCases.filterByCategory(category)) {
                is Either.Left -> {
                }
                is Either.Right -> {
                    _model.value = Event(SecondaryModel.FilteredRecipeList(response.r))
                }
            }
        }
    }
}