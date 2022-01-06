package com.example.recipefinder

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.Either
import com.example.use.CountryUseCases
import com.orhanobut.logger.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(countryUseCases: CountryUseCases) : ViewModel() {
    init {
        viewModelScope.launch {
            //Prueba filter by area

            when (val response = countryUseCases.getAllCountries()) {
                is Either.Left -> {
                    Logger.d("error en la API: ${response.l}")
                }
                is Either.Right -> {
                    Logger.d("Area prueba nombre: ${response.r}")
                }
            }
            val location = countryUseCases.getLocation()
            Log.d("PRETTY_LOGGER", "location: $location")
        }
    }
}