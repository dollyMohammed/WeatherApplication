package com.example.myapplication.repostory

import com.example.myapplication.data.WeatherDao
import com.example.myapplication.modole.City
import com.example.myapplication.modole.Favourite
import com.example.myapplication.modole.Unit
import java.util.concurrent.Flow
import javax.inject.Inject


class WeatherDbRebasitory @Inject constructor(private val weatherDoa:WeatherDao) {
    fun getFavourites() : kotlinx.coroutines.flow.Flow<List<Favourite>> = weatherDoa.getFavourites()
    suspend fun insertFavourites(favourite: Favourite)= weatherDoa.insertFavourite(favourite)
    suspend fun updateFavourite(favourite: Favourite)= weatherDoa.updateFavourite(favourite)
    suspend fun deleteAllFavourites()= weatherDoa.deleteAllFavourite()
    suspend fun deleteFavourite(favourite: Favourite)= weatherDoa.deleteFavourite(favourite)
    suspend fun getFavById(city: String) : Favourite= weatherDoa.getFavByed(city)

    fun getUnits(): kotlinx.coroutines.flow.Flow<List<Unit>> = weatherDoa.getUnits()
    suspend fun insertUnit(unit: Unit) = weatherDoa.insertUnit(unit)
    suspend fun updateUnit(unit: Unit)=weatherDoa.updateUnit(unit)
    suspend fun deleteAllUnits()=weatherDoa.deletAllunits()
    suspend fun deleteUnits(unit: Unit)=weatherDoa.deleteunit(unit)


}