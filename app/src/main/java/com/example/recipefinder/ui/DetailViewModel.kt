package com.example.recipefinder.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipefinder.RecipeParcelable
import com.example.recipefinder.data.toRecipe
import com.example.use.RecipeUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class DetailViewModel @Inject constructor(private val recipeUseCases: RecipeUseCases,
                                          @Named("RecipeParcelable") recipeParcelable: RecipeParcelable) : ViewModel() {

    private var _isFav = MutableLiveData<Boolean>()
    val isFav: LiveData<Boolean>
        get() = _isFav

    data class IngredientItem(val name: String?, val measure: String?)
    val ingredientsList = mutableListOf<IngredientItem>()

    val recipe = recipeParcelable.toRecipe()

    init {
        _isFav.value = false
        viewModelScope.launch {
            recipeUseCases.findRecipeByID(recipe.idMeal!!)?.let {
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

    fun fillIngredientsList() {
        with(recipe) {
            addToIngredientList(strIngredient1, strMeasure1)
            addToIngredientList(strIngredient2, strMeasure2)
            addToIngredientList(strIngredient3, strMeasure3)
            addToIngredientList(strIngredient4, strMeasure4)
            addToIngredientList(strIngredient5, strMeasure5)
            addToIngredientList(strIngredient6, strMeasure6)
            addToIngredientList(strIngredient7, strMeasure7)
            addToIngredientList(strIngredient8, strMeasure8)
            addToIngredientList(strIngredient9, strMeasure9)
            addToIngredientList(strIngredient10, strMeasure10)
            addToIngredientList(strIngredient11, strMeasure11)
            addToIngredientList(strIngredient12, strMeasure12)
            addToIngredientList(strIngredient13, strMeasure13)
            addToIngredientList(strIngredient14, strMeasure14)
            addToIngredientList(strIngredient15, strMeasure15)
            addToIngredientList(strIngredient16, strMeasure16)
            addToIngredientList(strIngredient17, strMeasure17)
            addToIngredientList(strIngredient18, strMeasure18)
            addToIngredientList(strIngredient19, strMeasure19)
            addToIngredientList(strIngredient20, strMeasure20)
        }
    }

    private fun addToIngredientList(name: String?, measure: String?) {
        if (!name.isNullOrEmpty()) {
            ingredientsList.add(IngredientItem(name, measure))
        }
    }
}