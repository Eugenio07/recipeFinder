package com.example.recipefinder.ui

import androidx.lifecycle.ViewModel
import com.example.use.RecipeUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(private val recipeUseCases: RecipeUseCases) : ViewModel() { // TODO: Implement the ViewModel
}