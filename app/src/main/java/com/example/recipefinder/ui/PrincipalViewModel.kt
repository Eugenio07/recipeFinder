package com.example.recipefinder.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.orhanobut.logger.Logger

class PrincipalViewModel : ViewModel() {
    private val _model = MutableLiveData<PrincipalModel>()
    val model: LiveData<PrincipalModel>
        get() = _model


    enum class Filters{
        COUNTRIES,
        CATEGORIES,
        INGREDIENT
    }

    sealed class PrincipalModel {
        class GoToSecondary(val filter: Filters) : PrincipalModel()
        object GoToList : PrincipalModel()
        object GoToDetail : PrincipalModel()
    }

    init {
        Logger.i("inicioViewModel principal")
    }

    fun randomClicked() {
        Logger.i("random")
        _model.value = PrincipalModel.GoToDetail
    }

    fun filterClicked(filter: Filters){
        Logger.i("filter")
        _model.value = PrincipalModel.GoToSecondary(filter)
    }

    fun recipeNameSearched(){
        Logger.i("name")
        _model.value = PrincipalModel.GoToList
    }
}