package com.example.recipefinder.data.server.restCountries

import com.example.data.source.CountriesDataSource
import com.example.domain.Country
import com.example.domain.Either
import com.example.recipefinder.data.toDomainCountry
import com.orhanobut.logger.Logger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class RestCountryDataSource: CountriesDataSource {
    override suspend fun getAllCountries(): Either<String, List<Country>> {
        return withContext(Dispatchers.IO) {
            try {
                Either.Right(RestCountry.retrofitService.getAllCountries().map { it.toDomainCountry() })
            } catch (e: HttpException) {
                Either.Left("Connection failure")
            } catch (e: Exception) {
                Either.Left(e.message ?: "Connection failure")
            }
        }
    }
}