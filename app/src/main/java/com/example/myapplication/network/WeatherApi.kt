package com.example.myapplication.network

import com.example.myapplication.modole.Weather
import com.example.myapplication.modole.WeatherObject
import com.example.myapplication.utils.Constant
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface WeatherApi {
    @GET(value= "data/2.5/forecast/daily")
    suspend fun getWeather(
        @Query(value = "q") query: String,
        @Query(value = "units") units: String= "imperial",
        @Query(value = "appid") appid: String= Constant.API_KEY,
    ): Weather
}