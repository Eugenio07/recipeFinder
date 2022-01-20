package com.example.recipefinder.data.server.restCountries

data class RestCountryResponse(
    val name: String?,
    val demonym: String?,
    val alpha2Code: String?,
    val flags: Flags?
) {
    data class Flags(
        val svg: String?,
        val png: String?
    )
}