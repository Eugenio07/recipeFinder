package com.example.recipefinder.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.Either
import com.example.domain.Event
import com.example.domain.Recipe
import com.example.use.RecipeUseCases
import com.orhanobut.logger.Logger
import kotlinx.coroutines.launch

class PrincipalViewModel(private val recipeUseCases: RecipeUseCases) : ViewModel() {
    private val _model = MutableLiveData<Event<PrincipalModel>>()
    val model: LiveData<Event<PrincipalModel>>
        get() = _model

    sealed class PrincipalModel {
        class GoToSecondary(val filter: String) : PrincipalModel()
        class GoToList(val listOfRecipes: List<Recipe>) : PrincipalModel()
        class GoToDetail(val recipe: Recipe) : PrincipalModel()
    }

    init {
        Logger.i("inicioViewModel principal")
    }

    fun randomClicked() {
        Logger.i("random")
        viewModelScope.launch {
            when (val response = recipeUseCases.getRandomMeal()) {
                is Either.Left -> {
                    Logger.d("error en la API: ${response.l}")
                }
                is Either.Right -> {
                    Logger.d("getRandom prueba nombre: ${response.r[0]}")
                    _model.value = Event(PrincipalModel.GoToDetail(response.r[0]))
                }
            }
        }
    }

    fun searchedByName(name: String) {
        viewModelScope.launch {
            when (val response = recipeUseCases.getByName(name)) {
                is Either.Left -> {
                    Logger.d("error en la API: ${response.l}")
                }
                is Either.Right -> {
                    Logger.d("getByName prueba nombre: ${response.r[0]}")
                    _model.value = Event(PrincipalModel.GoToList(response.r))
                }
            }
        }
    }

    fun filterClicked(filter: String) {
        Logger.i("filter: $filter")
        _model.value = Event(PrincipalModel.GoToSecondary(filter))
    }

    fun favoriteClicked() {
        viewModelScope.launch {
            val response = recipeUseCases.getFavoritesRecipes()
            _model.value = Event(PrincipalModel.GoToList(response))
        }
    }
}