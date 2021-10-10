package com.example.recipefinder.data.server.restCountries

import com.example.recipefinder.data.server.restCountries.RestCountryResponse.RestCountryResponseItem


class RestCountryResponse : ArrayList<RestCountryResponseItem>(){
    data class RestCountryResponseItem(
        val name: String?,
        val demonym: String?,
        val flags: Flags?
    ) {
        data class Flags(
            val svg: String?,
            val png: String?
        )
    }
}
