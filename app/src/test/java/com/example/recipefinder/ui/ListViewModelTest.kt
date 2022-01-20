package com.example.recipefinder.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.domain.Either
import com.example.domain.Event
import com.example.recipefinder.data.server.theMealDB.NETWORK_STATUS
import com.example.recipefinder.ui.list.ListViewModel
import com.example.recipefinder.ui.list.ListViewModel.ListModel
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
class ListViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var recipeUseCases: RecipeUseCases

    @Mock
    lateinit var observer: Observer<Event<ListModel>>

    private lateinit var vm: ListViewModel

    @Before
    fun setUp() {
        vm = ListViewModel(recipeUseCases, Dispatchers.Unconfined)
    }

    @Test
    fun `recipe clicked`(){
        runBlocking {
            vm.model.observeForever(observer)
            val recipes = Either.Right(listOf(mockedRecipe))
            whenever(recipeUseCases.getByID(recipes.r[0].idMeal!!)).thenReturn(recipes)

            vm.recipeClicked(recipes.r[0])

            verify(observer).onChanged(Event(ListModel.Network(NETWORK_STATUS.LOADING)))
            verify(observer).onChanged(Event(ListModel.Network(NETWORK_STATUS.DONE)))
            verify(observer).onChanged(Event(ListModel.GoToDetail(recipes.r[0])))
        }
    }

    @Test
    fun `Dont find a recipe`(){
        runBlocking {
            vm.model.observeForever(observer)
            val recipe = mockedRecipe
            val response = Either.Left("Connection failure")
            whenever(recipeUseCases.getByID(recipe.idMeal!!)).thenReturn(response)
            vm.recipeClicked(recipe)
            verify(observer).onChanged(Event(ListModel.Network(NETWORK_STATUS.LOADING)))
            verify(observer).onChanged(Event(ListModel.Network(NETWORK_STATUS.ERROR)))
        }
    }

}