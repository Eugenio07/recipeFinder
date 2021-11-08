package com.example.recipefinder.ui.principal

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.domain.Event
import com.example.recipefinder.ui.principal.PrincipalViewModel.*
import com.example.use.RecipeUseCases
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.orhanobut.logger.Logger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import mockedDomain.mockedRecipe
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PrincipalViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var recipeUseCases: RecipeUseCases

    @Mock
    lateinit var observer: Observer<Event<PrincipalModel>>


    private lateinit var vm: PrincipalViewModel

    @Before
    fun setUp() {
        vm = PrincipalViewModel(recipeUseCases, Dispatchers.Unconfined)
    }

    @Test
    fun `filter clicked`() {
        vm.model.observeForever(observer)

        vm.filterClicked("area")

        verify(observer).onChanged(Event(PrincipalModel.GoToSecondary("area")))

    }

    @Test
    fun `favorite clicked`() {
        runBlocking {
            val recipes = listOf(mockedRecipe)
            whenever(recipeUseCases.getFavoritesRecipes()).thenReturn(recipes)
            vm.model.observeForever(observer)

            vm.favoriteClicked()

            verify(observer).onChanged(Event(PrincipalModel.GoToList(recipes)))

        }
    }
}