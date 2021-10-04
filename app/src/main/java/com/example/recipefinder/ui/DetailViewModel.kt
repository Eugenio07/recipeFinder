package com.example.recipefinder.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.Recipe
import com.example.use.RecipeUseCases
import com.orhanobut.logger.Logger
import kotlinx.coroutines.launch

class DetailViewModel(private val recipeUseCases: RecipeUseCases, private val recipe: Recipe) : ViewModel() {

    private var _isFav = MutableLiveData<Boolean>()
    val isFav: LiveData<Boolean>
        get() = _isFav

    init {
        _isFav.value = false
    }

    fun recipeIsFav(){
        viewModelScope.launch {
            Logger.d("id: ${recipe.idMeal}")
            recipeUseCases.findRecipeByID(recipe.idMeal!!)?.let {
                Logger.d("wtf: ${it.idMeal}")
                _isFav.value = true
            }
        }
    }

    fun favClicked() {
        viewModelScope.launch {
            _isFav.value = !_isFav.value!!
            if (_isFav.value == true) {
                recipeUseCases.saveFavoriteRecipe(recipe)
            } else {
                recipeUseCases.deleteFavoriteRecipe(recipe)
            }
        }

    }

}