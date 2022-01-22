package com.example.recipefinder

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.domain.Event
import com.example.recipefinder.data.toRecipe
import com.example.recipefinder.ui.list.ListViewModel
import com.example.recipefinder.ui.list.ListViewModel.ListModel
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
class ListIntegrationTest {
    @get: Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    @Named("recipeUsesCasesProviderTest")
    lateinit var recipeUseCases: RecipeUseCases

    @Mock
    lateinit var observer: Observer<Event<ListModel>>

    private lateinit var vm: ListViewModel

    private val  recipeParcelable = RecipeParcelable()

    @Before
    fun setUp(){
        hiltRule.inject()
        vm = ListViewModel(recipeUseCases, Dispatchers.Unconfined)
    }

    @Test
    fun recipeClicked(){
        vm.model.observeForever(observer)
        vm.recipeClicked(recipeParcelable.toRecipe())
        verify(observer).onChanged(Event(ListModel.GoToDetail(recipeParcelable.toRecipe())))
    }
}