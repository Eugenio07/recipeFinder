package com.example.recipefinder.di

import com.example.data.repository.CountriesRepository
import com.example.data.repository.PermissionCheck
import com.example.data.repository.PermissionCheckTest
import com.example.data.repository.RecipeRepository
import com.example.data.source.*
import com.example.recipefinder.PermissionChecker
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object DataModuleTest {
    @Provides
    @Named("countriesRepositoryProviderTest")
    fun countriesRepositoryProvider(
        localDataSource: LocalDataSourceTest,
        countriesDataSource: CountriesDataSourceTest,
        permissionChecker: PermissionCheckTest
    ) = CountriesRepository(localDataSource, countriesDataSource, permissionChecker)

    @Provides
    @Named("recipeRepositoryProviderTest")
    fun recipeRepositoryProvider(
        localDataSource: LocalDataSourceTest,
        remoteDataSource: RemoteDataSourceTest,
        @Named("countriesRepositoryProviderTest") countriesRepository: CountriesRepository
    ) = RecipeRepository(localDataSource, remoteDataSource, countriesRepository)
}