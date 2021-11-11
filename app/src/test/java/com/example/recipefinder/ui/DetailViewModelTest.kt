package com.example.recipefinder.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.recipefinder.RecipeParcelable
import com.example.recipefinder.data.toRecipe
import com.example.recipefinder.ui.detail.DetailViewModel
import com.example.use.RecipeUseCases
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import mockedDomain.mockedRecipe
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var recipeUseCases: RecipeUseCases

    @Mock
    lateinit var recipeParcelable: RecipeParcelable

    @Mock
    lateinit var observer: Observer<Boolean>

    private lateinit var vm: DetailViewModel

    @Before
    fun setUp() {
        vm = DetailViewModel(recipeUseCases, recipeParcelable, Dispatchers.Unconfined)
    }

    @Test
    fun `Find favorite recipe`() {
        runBlocking {
            vm.isFav.observeForever(observer)
            val recipe = mockedRecipe.copy(idMeal = "1")
            whenever(recipeUseCases.findRecipeByID("1")).thenReturn(recipe)
            vm.findRecipe("1")
            verify(observer).onChanged(true)
        }
    }

    @Test
    fun `Don't find favorite recipe`() {
        runBlocking {
            vm.isFav.observeForever(observer)
            verify(observer).onChanged(false)
        }
    }

    @Test
    fun `Fav is clicked`() {
        runBlocking {
            vm.favClicked()
            verify(recipeUseCases).saveFavoriteRecipe(recipeParcelable.toRecipe())

            vm.favClicked()
            verify(recipeUseCases).deleteFavoriteRecipe(recipeParcelable.toRecipe())
        }
    }


}