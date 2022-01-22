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
        var response = "Unknown"
        if (permissionChecker.request(arrayListOf(COARSE_LOCATION, FINE_LOCATION)).first) {
            val location = countriesDataSource.getLocation() ?: "Unknown"
            println(location)
            if (localDataSource.isInRecipeList(location)) {
                response = location
            }
        }
        println(response)
        return response
    }

    suspend fun getCountryByCode(code: String): Country = localDataSource.getCountryByCode(code)

}

interface PermissionCheckTest: PermissionCheck

interface PermissionCheck {
    enum class Permission {
        COARSE_LOCATION,
        FINE_LOCATION
    }

    suspend fun request(permission: List<Permission>): Pair<Boolean, Boolean>
}