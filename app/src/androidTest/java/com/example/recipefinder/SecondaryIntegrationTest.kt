package com.example.recipefinder

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.domain.Country
import com.example.domain.Event
import com.example.recipefinder.data.server.theMealDB.NETWORK_STATUS
import com.example.recipefinder.data.toRecipe
import com.example.recipefinder.ui.secondary.SecondaryViewModel
import com.example.recipefinder.ui.secondary.SecondaryViewModel.SecondaryModel
import com.example.use.CountryUseCases
import com.example.use.RecipeUseCases
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.Dispatchers
import mockedDomain.mockedCountry
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
class SecondaryIntegrationTest {
    @get: Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    @Named("recipeUsesCasesProviderTest")
    lateinit var recipeUseCases: RecipeUseCases

    @Inject
    @Named("countryUsesCasesProviderTest")
    lateinit var countryUseCases: CountryUseCases

    @Mock
    lateinit var observer: Observer<Event<SecondaryModel>>

    private val recipeParcelable = RecipeParcelable()
    private var fakeListOfRecipes = mutableListOf(recipeParcelable.toRecipe())
    private var fakeListOfCountries = mutableListOf(mockedCountry)

    private lateinit var vm: SecondaryViewModel

    @Before
    fun setUp() {
        hiltRule.inject()
        vm = SecondaryViewModel(recipeUseCases, countryUseCases, Dispatchers.Unconfined)
    }

    @Test
    fun filterByCategory() {
        vm.model.observeForever(observer)
        vm.filterByCategory("Seafood")
        verify(observer).onChanged(Event(SecondaryModel.Network(NETWORK_STATUS.LOADING)))
        verify(observer).onChanged(Event(SecondaryModel.FilteredRecipeList(fakeListOfRecipes)))
    }

    @Test
    fun getListOfFilterCountry() {
        vm.model.observeForever(observer)
        vm.getListOfFilters("Countries")

        verify(observer).onChanged(Event(SecondaryModel.Network(NETWORK_STATUS.LOADING)))
        verify(observer).onChanged(Event(SecondaryModel.Network(NETWORK_STATUS.DONE)))
        verify(observer).onChanged(Event(SecondaryModel.AreaList(fakeListOfCountries, mockedCountry)))
    }

}