package com.example.recipefinder.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.recipefinder.data.database.entity.CountryDB

@Dao
interface CountryDao {

    @Query("SELECT COUNT(name) FROM CountryDB")
    fun countryCount(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertCountries(countries: List<CountryDB>)

    @Query("SELECT * FROM CountryDB")
    fun getCountries(): List<CountryDB>
}