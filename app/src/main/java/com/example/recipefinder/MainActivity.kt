package com.example.recipefinder

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.data.repository.CountriesRepository
import com.example.data.repository.RecipeRepository
import com.example.recipefinder.data.database.db.RecipeDataBase
import com.example.recipefinder.data.database.db.RoomDataSource
import com.example.recipefinder.data.server.restCountries.RestCountryDataSource
import com.example.recipefinder.data.server.theMealDB.TheMealDBDataSource
import com.example.recipefinder.databinding.ActivityMainBinding
import com.example.use.CountryUseCases
import com.example.use.RecipeUseCases
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val mainActivityViewModel: MainActivityViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Para inicializar el Logger
        Logger.addLogAdapter(AndroidLogAdapter())

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
    }
}
