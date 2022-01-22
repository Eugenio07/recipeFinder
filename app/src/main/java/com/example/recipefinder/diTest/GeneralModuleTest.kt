package com.example.recipefinder.di

import androidx.lifecycle.SavedStateHandle
import com.example.data.repository.CountriesRepository
import com.example.data.repository.RecipeRepository
import com.example.recipefinder.RecipeParcelable
import com.example.use.CountryUseCases
import com.example.use.RecipeUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object GeneralModuleTest {
    @Provides
    @Named("countryUsesCasesProviderTest")
    fun countryUsesCasesProvider(
        @Named("countriesRepositoryProviderTest") countriesRepository: CountriesRepository
    ) = CountryUseCases(countriesRepository)

    @Provides
    @Named("recipeUsesCasesProviderTest")
    fun recipeUsesCasesProvider(
        @Named("recipeRepositoryProviderTest")
        recipeRepository: RecipeRepository
    ) = RecipeUseCases(recipeRepository)

//    @Provides
//    @Named("RecipeParcelable")
//    fun recipeParcelableProvider(args: SavedStateHandle): RecipeParcelable = args.get<RecipeParcelable>("RecipeParcelable")?: throw IllegalStateException("Recipe null")
//
//    @Provides
//    fun dispatcherProvider(): CoroutineDispatcher = Dispatchers.Main
}