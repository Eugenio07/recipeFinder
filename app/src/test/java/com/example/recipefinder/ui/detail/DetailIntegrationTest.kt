package com.example.recipefinder.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.data.repository.CountriesRepository
import com.example.data.repository.PermissionCheck
import com.example.data.repository.RecipeRepository
import com.example.data.source.CountriesDataSource
import com.example.data.source.LocalDataSource
import com.example.data.source.RemoteDataSource
import com.example.recipefinder.*
import com.example.recipefinder.ui.principal.PrincipalViewModel
import com.example.use.RecipeUseCases
import com.nhaarman.mockitokotlin2.*
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
class DetailIntegrationTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()


    private lateinit var localDataSource: FakeLocalDataSource
    private lateinit var remoteDataSource: FakeRemoteDataSource
    private lateinit var countriesDataSource: FakeCountryDataSource
    private lateinit var permissionChecker: FakePermissionChecker
    private lateinit var countryRepository: CountriesRepository
    private lateinit var recipeRepository: RecipeRepository
    private lateinit var recipeUseCases: RecipeUseCases
    @Mock
    private lateinit var recipeParcelable: RecipeParcelable
    @Mock
    lateinit var observer: Observer<Boolean>

    private lateinit var vm: DetailViewModel
    @Before
    fun setUp() {
        localDataSource = FakeLocalDataSource()
        remoteDataSource = FakeRemoteDataSource()
        countriesDataSource = FakeCountryDataSource()
        permissionChecker = FakePermissionChecker()
        countryRepository = CountriesRepository(localDataSource, countriesDataSource, permissionChecker)
        recipeRepository = RecipeRepository(localDataSource, remoteDataSource, countryRepository)
        recipeUseCases = RecipeUseCases(recipeRepository)

        vm = DetailViewModel(recipeUseCases, recipeParcelable, Dispatchers.Unconfined)
    }

    @Test
    fun `Find favorite recipe`() {
        runBlocking {
            vm.isFav.observeForever(observer)
            vm.findRecipe("1")
            verify(observer).onChanged(true)
        }
    }

}