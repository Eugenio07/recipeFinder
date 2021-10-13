package com.example.use

import com.example.data.repository.CountriesRepository
import com.example.domain.Country
import com.example.domain.Either

class CountryUseCases(private val countriesRepository: CountriesRepository) {
    suspend fun getAllCountries(): Either<String, List<Country>> = countriesRepository.getAllCountries()
    suspend fun getLocation(): String = countriesRepository.getLocation()
}