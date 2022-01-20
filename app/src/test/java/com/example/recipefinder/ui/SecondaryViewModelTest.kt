package com.example.recipefinder.ui

import android.os.Handler
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.domain.Either
import com.example.domain.Event
import com.example.recipefinder.data.server.theMealDB.NETWORK_STATUS
import com.example.recipefinder.ui.secondary.SecondaryViewModel
import com.example.recipefinder.ui.secondary.SecondaryViewModel.SecondaryModel
import com.example.use.CountryUseCases
import com.example.use.RecipeUseCases
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import mockedDomain.mockedCategory
import mockedDomain.mockedRecipe
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SecondaryViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var recipeUseCases: RecipeUseCases

    @Mock
    lateinit var countryUseCases: CountryUseCases

    @Mock
    lateinit var observer: Observer<Event<SecondaryModel>>

    private lateinit var vm: SecondaryViewModel

    @Before
    fun setUp() {
        vm = SecondaryViewModel(recipeUseCases, countryUseCases, Dispatchers.Unconfined)
    }

    @Test
    fun `Filter by category`() {
        runBlocking {
            vm.model.observeForever(observer)
            val recipes = Either.Right(listOf(mockedRecipe))
            whenever(recipeUseCases.filterByCategory("Seafood")).thenReturn(recipes)
            vm.filterByCategory("Seafood")
            verify(observer).onChanged(Event(SecondaryModel.Network(NETWORK_STATUS.LOADING)))
            verify(observer).onChanged(Event(SecondaryModel.FilteredRecipeList(recipes.r)))
        }
    }

    @Test
    fun `Get list of categories`(){
        runBlocking {
            vm.model.observeForever(observer)
            val categories = Either.Right(listOf(mockedCategory))
            whenever(recipeUseCases.getListOfCategories()).thenReturn(categories)
            vm.getListOfFilters("Category")
            verify(observer).onChanged(Event(SecondaryModel.Network(NETWORK_STATUS.LOADING)))
            verify(observer).onChanged(Event(SecondaryModel.Network(NETWORK_STATUS.DONE)))
            verify(observer).onChanged(Event(SecondaryModel.CategoryList(categories.r)))
        }
    }


    @Test
    fun `Dont find a list`(){
        runBlocking {
            vm.model.observeForever(observer)
            val categories = Either.Left("Connection failure")
            whenever(recipeUseCases.getListOfCategories()).thenReturn(categories)
            vm.getListOfFilters("Category")
            verify(observer).onChanged(Event(SecondaryModel.Network(NETWORK_STATUS.LOADING)))
            verify(observer).onChanged(Event(SecondaryModel.Network(NETWORK_STATUS.ERROR)))
        }
    }
}