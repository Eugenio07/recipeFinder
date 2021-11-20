package com.example.recipefinder.data.server.restCountries

import android.annotation.SuppressLint
import android.app.Application
import android.location.Geocoder
import android.util.Log
import com.example.data.source.CountriesDataSource
import com.example.domain.Country
import com.example.domain.Either
import com.example.recipefinder.data.toDomainCountry
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import kotlin.coroutines.resume

class RestCountryDataSource(application: Application) : CountriesDataSource {

    private val geocoder = Geocoder(application)
    private val fusedLocationClient = LocationServices.getFusedLocationProviderClient(application)

    override suspend fun getAllCountries(): Either<String, List<Country>> {
        return withContext(Dispatchers.IO) {
            try {
                Either.Right(
                    RestCountry.retrofitService.getAllCountries().map { it.toDomainCountry() })
            } catch (e: HttpException) {
                Either.Left("Connection failure")
            } catch (e: Exception) {
                Either.Left(e.message ?: "Connection failure")
            }
        }
    }


    @SuppressLint("MissingPermission")
    override suspend fun getLocation(): String? =
        suspendCancellableCoroutine { continuation ->
            fusedLocationClient.lastLocation
                .addOnSuccessListener {
                    continuation.resume(
                        geocoder.getFromLocation(it.latitude, it.longitude, 1)
                            .firstOrNull()?.countryName
                    )
                }
        }
}