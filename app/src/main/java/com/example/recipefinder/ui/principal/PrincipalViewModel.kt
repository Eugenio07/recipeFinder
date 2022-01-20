package com.example.recipefinder.ui.principal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.domain.Either
import com.example.domain.Event
import com.example.domain.Recipe
import com.example.recipefinder.ScopedViewModel
import com.example.use.RecipeUseCases
import com.orhanobut.logger.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PrincipalViewModel @Inject constructor(
    private val recipeUseCases: RecipeUseCases,
    uiDispatcher: CoroutineDispatcher
) : ScopedViewModel(uiDispatcher) {

    private val _model = MutableLiveData<Event<PrincipalModel>>()
    val model: LiveData<Event<PrincipalModel>>
        get() = _model

    init {
        initScope()
    }

    sealed class PrincipalModel {
        data class GoToSecondary(val filter: String) : PrincipalModel()
        data class GoToList(val listOfRecipes: List<Recipe>) : PrincipalModel()
        data class GoToDetail(val recipe: Recipe) : PrincipalModel()
    }

    fun randomClicked() {
        Logger.i("random")
        launch {
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
        launch {
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
        launch {
            _model.value = Event(PrincipalModel.GoToList(recipeUseCases.getFavoritesRecipes()))
        }
    }
}