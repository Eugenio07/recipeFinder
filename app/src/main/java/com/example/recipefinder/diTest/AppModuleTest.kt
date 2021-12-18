package com.example.recipefinder.di

import android.app.Application
import com.example.data.repository.PermissionCheck
import com.example.data.source.CountriesDataSource
import com.example.data.source.LocalDataSource
import com.example.data.source.LocalDataSourceTest
import com.example.data.source.RemoteDataSource
import com.example.recipefinder.FakeLocalDataSource
import com.example.recipefinder.PermissionChecker
import com.example.recipefinder.data.database.db.RecipeDataBase
import com.example.recipefinder.data.database.db.RoomDataSource
import com.example.recipefinder.data.server.restCountries.RestCountryDataSource
import com.example.recipefinder.data.server.theMealDB.TheMealDBDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModuleTest {
    @Provides
    @Singleton
    fun provideSomeString(): String{
        return "Hola eugenio"
    }

//    @Provides
//    @Singleton
//    fun dataBaseProvider(app:Application) = RecipeDataBase.getInstance(app.applicationContext)

    @Provides
    fun localDataSourceProvider(): LocalDataSourceTest = FakeLocalDataSource()

//    @Provides
//    fun remoteDataSourceProvider(): RemoteDataSource = TheMealDBDataSource()
//
//    @Provides
//    fun countriesDataSourceProvider(app:Application): CountriesDataSource = RestCountryDataSource(app)
//
//    @Provides
//    fun permissionCheckerProvider(app: Application): PermissionCheck = PermissionChecker(app)
}