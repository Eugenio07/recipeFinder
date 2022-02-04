package com.example.recipefinder.ui.principal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.domain.Either
import com.example.domain.Event
import com.example.domain.Recipe
import com.example.recipefinder.ScopedViewModel
import com.example.recipefinder.data.server.theMealDB.NETWORK_STATUS
import com.example.recipefinder.ui.principal.PrincipalViewModel.PrincipalModel.*
import com.example.use.RecipeUseCases
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
        object ShowError : PrincipalModel()
        data class GoToList(val listOfRecipes: List<Recipe>) : PrincipalModel()
        data class GoToDetail(val recipe: Recipe) : PrincipalModel()
        data class Network(val networkStatus: NETWORK_STATUS) : PrincipalModel()
    }

    fun randomClicked() {
        _model.value = Event(Network(NETWORK_STATUS.LOADING))
        launch {
            when (val response = recipeUseCases.getRandomMeal()) {
                is Either.Left -> {
                    _model.value = Event(Network(NETWORK_STATUS.DONE))
                    _model.value = Event(ShowError)
                }
                is Either.Right -> {
                    _model.value = Event(Network(NETWORK_STATUS.DONE))
                    _model.value = Event(PrincipalModel.GoToDetail(response.r[0]))
                }
            }
        }
    }

    fun searchedByName(name: String) {
        _model.value = Event(Network(NETWORK_STATUS.LOADING))
        launch {
            when (val response = recipeUseCases.getByName(name)) {
                is Either.Left -> {
                    _model.value = Event(Network(NETWORK_STATUS.DONE))
                    _model.value = Event(ShowError)
                }
                is Either.Right -> {
                    _model.value = Event(Network(NETWORK_STATUS.DONE))
                    _model.value = Event(GoToList(response.r))
                }
            }
        }
    }

    fun filterClicked(filter: String) {
        _model.value = Event(GoToSecondary(filter))
    }

    fun favoriteClicked() {
        launch {
            _model.value = Event(GoToList(recipeUseCases.getFavoritesRecipes()))
        }
    }
}