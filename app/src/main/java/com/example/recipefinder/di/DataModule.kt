package com.example.recipefinder.di

import com.example.data.repository.CountriesRepository
import com.example.data.repository.PermissionCheck
import com.example.data.repository.RecipeRepository
import com.example.data.source.CountriesDataSource
import com.example.data.source.LocalDataSource
import com.example.data.source.RemoteDataSource
import com.example.recipefinder.PermissionChecker
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    fun countriesRepositoryProvider(
        localDataSource: LocalDataSource,
        countriesDataSource: CountriesDataSource,
        permissionChecker: PermissionCheck
    ) = CountriesRepository(localDataSource, countriesDataSource, permissionChecker)

    @Provides
    fun recipeRepositoryProvider(
        localDataSource: LocalDataSource,
        remoteDataSource: RemoteDataSource,
        countriesRepository: CountriesRepository
    ) = RecipeRepository(localDataSource, remoteDataSource, countriesRepository)
}