package com.example.recipefinder.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.Either
import com.example.domain.Event
import com.example.domain.Recipe
import com.example.recipefinder.data.server.theMealDB.NETWORK_STATUS
import com.example.use.RecipeUseCases
import com.orhanobut.logger.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor (private val recipeUseCases: RecipeUseCases) : ViewModel() {
    private val _model = MutableLiveData<Event<ListModel>>()
    val model: LiveData<Event<ListModel>>
        get() = _model

    sealed class ListModel {
        class GoToDetail(val recipe: Recipe) : ListModel()
        class Network(val networkStatus: NETWORK_STATUS) : ListModel()
    }

    fun recipeClicked(recipe: Recipe) {
        Logger.i("click")
        _model.value = Event(ListModel.Network(NETWORK_STATUS.LOADING))
        viewModelScope.launch {
            when (val response = recipeUseCases.getByID(recipe.idMeal!!)) {
                is Either.Left -> {
                    Logger.d("error en la API: ${response.l}")
                    _model.value = Event(ListModel.Network(NETWORK_STATUS.ERROR))
                }
                is Either.Right -> {
                    _model.value = Event(ListModel.Network(NETWORK_STATUS.DONE))
                    _model.value = Event(ListModel.GoToDetail(response.r[0]))
                }
            }
        }
    }
}