package com.example.recipefinder.ui.principal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
    private val _model = MutableLiveData<PrincipalModel>()
    val model: LiveData<PrincipalModel>
        get() = _model

    private val _model2 = MutableLiveData<SealedTest>()
    val model2: LiveData<SealedTest>
        get() = _model2

    init {
        initScope()
    }

    sealed class PrincipalModel {
        data class GoToSecondary(val filter: String) : PrincipalModel()
        data class GoToList(val listOfRecipes: List<Recipe>) : PrincipalModel()
        data class GoToDetail(val recipe: Recipe) : PrincipalModel()
    }

    sealed class SealedTest{
        data class TestClass(val testInt: Int): SealedTest()
    }

    fun test(){
        _model2.value = SealedTest.TestClass(1)
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
                    _model.value = PrincipalModel.GoToDetail(response.r[0])
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
                    _model.value = PrincipalModel.GoToList(response.r)
                }
            }
        }
    }

    fun filterClicked(filter: String) {
        Logger.i("filter: $filter")
        _model.value = PrincipalModel.GoToSecondary(filter)
    }

    fun favoriteClicked() {
        launch {
            _model.value = PrincipalModel.GoToList(recipeUseCases.getFavoritesRecipes())
        }
    }
}