package com.example.recipefinder.di

import android.app.Application
import com.example.data.repository.PermissionCheck
import com.example.data.repository.PermissionCheckTest
import com.example.data.source.*
import com.example.recipefinder.*
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

    @Provides
    fun remoteDataSourceProvider(): RemoteDataSourceTest = FakeRemoteDataSource()

    @Provides
    fun countriesDataSourceProvider(): CountriesDataSourceTest = FakeCountryDataSource()

    @Provides
    fun permissionCheckerProvider(): PermissionCheckTest = FakePermissionChecker()
}