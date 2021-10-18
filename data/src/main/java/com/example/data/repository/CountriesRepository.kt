package com.example.data.repository

import com.example.data.repository.PermissionCheck.Permission.*
import com.example.data.source.CountriesDataSource
import com.example.data.source.LocalDataSource
import com.example.domain.Country
import com.example.domain.Either

class CountriesRepository(
    private val localDataSource: LocalDataSource,
    private val countriesDataSource: CountriesDataSource,
    private val permissionChecker: PermissionCheck
) {
    suspend fun getAllCountries(): Either<String, List<Country>> {
        if (localDataSource.countryListIsEmpty()) {
            when (val countries = countriesDataSource.getAllCountries()) {
                is Either.Left -> return Either.Left(countries.l)
                is Either.Right -> localDataSource.saveCountryList(countries.r)
            }
        }
        return Either.Right(localDataSource.getCountryList())
    }

    suspend fun getLocation(): String {
        return if (permissionChecker.request(arrayListOf(COARSE_LOCATION, FINE_LOCATION)).first) {
            val country = countriesDataSource.getLocation() ?: "Unknown"
            if (localDataSource.isInRecipeList(country)) {
                country
            } else {
                "Unknown"
            }
        } else {
            "Unknown"
        }
    }
}

interface PermissionCheck {
    enum class Permission {
        COARSE_LOCATION,
        FINE_LOCATION
    }

    suspend fun request(permission: List<Permission>): Pair<Boolean, Boolean>
}