package com.example.recipefinder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.data.repository.RecipeRepository
import com.example.recipefinder.data.database.db.RecipeDataBase
import com.example.recipefinder.data.database.db.RoomDataSource
import com.example.recipefinder.data.server.theMealDB.TheMealDBDataSource
import com.example.recipefinder.databinding.ActivityMainBinding
import com.example.use.RecipeUseCases
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger

class MainActivity : AppCompatActivity() {
    private lateinit var mainActivityViewModel: MainActivityViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Para inicializar el Logger
        Logger.addLogAdapter(AndroidLogAdapter())

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        mainActivityViewModel = getViewModel { MainActivityViewModel(RecipeUseCases(
            RecipeRepository(
                RoomDataSource(RecipeDataBase.getInstance(this)),
                TheMealDBDataSource()
            )
        )) }
    }
}