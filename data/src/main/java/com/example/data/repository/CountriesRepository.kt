package com.example.data.repository

import com.example.data.source.CountriesDataSource
import com.example.data.source.LocalDataSource
import com.example.domain.Country
import com.example.domain.Either
import com.example.domain.Recipe

class CountriesRepository(
    private val localDataSource: LocalDataSource,
    private val countriesDataStore: CountriesDataSource
) {
    suspend fun getAllCountries(): Either<String, List<Country>> =
        countriesDataStore.getAllCountries()
}