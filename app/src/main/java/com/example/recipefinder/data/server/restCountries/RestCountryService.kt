package com.example.recipefinder.data.server.restCountries

import com.example.recipefinder.data.server.getRetrofit
import retrofit2.http.GET

const val ROOT_URL_REST_COUNTRY = "https://restcountries.com/v2/"

const val ALL_COUNTRIES = "all?fields=name,demonym,flags,alpha2Code"

interface RestCountryService {
    @GET(ALL_COUNTRIES)
    suspend fun getAllCountries(): List<RestCountryResponse>
}

object RestCountry{
    val retrofitService: RestCountryService by lazy {
        getRetrofit(ROOT_URL_REST_COUNTRY).create(RestCountryService::class.java)
    }
}