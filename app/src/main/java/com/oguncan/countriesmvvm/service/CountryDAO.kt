package com.oguncan.countriesmvvm.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.oguncan.countriesmvvm.model.Country

//Data Access Object
@Dao
interface CountryDAO {

    @Insert
    suspend fun insertAll(vararg countries : Country) : List<Long>
    //List<Long> return Primary key from table

    @Query("SELECT * FROM country")
    suspend fun getAllCountries() : List<Country>

    @Query("SELECT * FROM country WHERE uuid = :countryId")
    suspend fun getCountry(countryId : Int) : Country

    @Query("DELETE FROM country")
    suspend fun deleteAllCountries()

}