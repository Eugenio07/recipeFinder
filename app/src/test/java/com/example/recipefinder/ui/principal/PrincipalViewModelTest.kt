package com.example.recipefinder.ui.principal

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.domain.Event
import com.example.recipefinder.ui.principal.PrincipalViewModel.*
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
class PrincipalViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var recipeUseCases: RecipeUseCases

    @Mock
    lateinit var observer: Observer<PrincipalModel>

    @Mock
    lateinit var observer2: Observer<SealedTest>

    private lateinit var vm: PrincipalViewModel

    @Before
    fun setUp() {
        vm = PrincipalViewModel(recipeUseCases, Dispatchers.Unconfined)
    }

    @Test
    fun `filter clicked`() {
        vm.model.observeForever(observer)

        vm.filterClicked("area")

        verify(observer).onChanged(PrincipalModel.GoToSecondary("area"))

    }

    @Test
    fun `favorite clicked`() {
        runBlocking {
            val recipes = listOf(mockedRecipe)
            whenever(recipeUseCases.getFavoritesRecipes()).thenReturn(recipes)
            vm.model.observeForever(observer)

            vm.favoriteClicked()

            verify(observer).onChanged(PrincipalModel.GoToList(recipes))
        }
    }

    @Test
    fun `test`() {
        runBlocking {


            vm.model2.observeForever(observer2)

            vm.test()

            verify(observer2).onChanged(SealedTest.TestClass(1))
        }
    }

}