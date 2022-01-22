package com.example.recipefinder

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.recipefinder.ui.detail.DetailViewModel
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
class DetailIntegrationTest {
    @get: Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    @Named("recipeUsesCasesProviderTest")
    lateinit var recipeUseCases: RecipeUseCases

    @Mock
    lateinit var observer: Observer<Boolean>
    private lateinit var vm: DetailViewModel

    private val  recipeParcelable = RecipeParcelable()

    @Before
    fun setUp(){
        hiltRule.inject()
        vm = DetailViewModel(recipeUseCases, recipeParcelable, Dispatchers.Unconfined)
    }

    @Test
    fun findRecipe(){
        vm.isFav.observeForever(observer)
        vm.findRecipe(recipeParcelable.idMeal?:"0")
        verify(observer).onChanged(true)
    }

    @Test
    fun dontFindRecipe(){
        vm.isFav.observeForever(observer)
        vm.findRecipe("1")
        verify(observer).onChanged(false)
    }
}