package com.example.recipefinder

import androidx.lifecycle.ViewModel
import com.example.use.CountryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(countryUseCases: CountryUseCases) : ViewModel() {
//    init {
//        viewModelScope.launch {
//            //Prueba filter by area
//
//            when (val response = countryUseCases.getAllCountries()) {
//                is Either.Left -> {
//                    Logger.d("error en la API: ${response.l}")
//                }
//                is Either.Right -> {
//                    Logger.d("Area prueba nombre: ${response.r}")
//                }
//            }
//            val location = countryUseCases.getLocation()
//            Log.d("PRETTY_LOGGER", "location: $location")
//        }
//    }
}