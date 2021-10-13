package com.example.recipefinder.data.database.dao

import androidx.room.*
import com.example.recipefinder.data.database.entity.CountryDB

@Dao
interface CountryDao {

    @Query("SELECT COUNT(name) FROM CountryDB")
    fun countryCount(): Int

    @Query("SELECT COUNT(recipeCountry) FROM CountryDB WHERE recipeCountry = 1")
    fun recipeCountryCount(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertCountries(countries: List<CountryDB>)

    @Query("SELECT * FROM CountryDB")
    fun getCountries(): List<CountryDB>

    @Query("SELECT * FROM CountryDB WHERE demonym IN (:demonym)")
    fun getCountriesByDemonym(demonym: List<String>): List<CountryDB>

    @Query("SELECT * FROM CountryDB WHERE name = :country")
    fun isInRecipeList(country: String): CountryDB?

    @Update
    fun updateCountries(countries: List<CountryDB>)

    @Query("SELECT * FROM CountryDB WHERE recipeCountry = 1")
    fun getRecipeCountries(): List<CountryDB>
}