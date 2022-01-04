package com.example.data.source

import com.example.domain.Country
import com.example.domain.Either

interface CountriesDataSourceTest: CountriesDataSource

interface CountriesDataSource {
    suspend fun getAllCountries(): Either<String, List<Country>>
    suspend fun getLocation():String?
}