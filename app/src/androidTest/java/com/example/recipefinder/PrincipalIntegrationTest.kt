package com.example.recipefinder

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.domain.Event
import com.example.domain.Recipe
import com.example.recipefinder.data.toRecipe
import com.example.recipefinder.ui.principal.PrincipalViewModel
import com.example.recipefinder.ui.principal.PrincipalViewModel.PrincipalModel
import com.example.use.RecipeUseCases
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.Dispatchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import javax.inject.Inject
import javax.inject.Named

@HiltAndroidTest
@RunWith(MockitoJUnitRunner::class)
class PrincipalIntegrationTest {

    @get: Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    @Named("recipeUsesCasesProviderTest")
    lateinit var recipeUseCases: RecipeUseCases

    @Mock
    lateinit var observer: Observer<Event<PrincipalModel>>
    private lateinit var vm: PrincipalViewModel

    private val  recipeParcelable = RecipeParcelable()
    private var fakeListOfRecipes = mutableListOf<Recipe>()

    @Before
    fun setUp(){
        hiltRule.inject()
        vm = PrincipalViewModel(recipeUseCases, Dispatchers.Unconfined)
    }

    @Test
    fun randomClicked(){
        vm.model.observeForever(observer)
        vm.randomClicked()
        verify(observer).onChanged(Event(PrincipalModel.GoToDetail(recipeParcelable.toRecipe())))
    }

    @Test
    fun findByName(){
        vm.model.observeForever(observer)
        vm.searchedByName("Arepa")
        fakeListOfRecipes.add(recipeParcelable.toRecipe())
        verify(observer).onChanged(Event(PrincipalModel.GoToList(fakeListOfRecipes)))
    }

}