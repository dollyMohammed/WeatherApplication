package com.example.myapplication.repostory

import android.util.Log
import com.example.myapplication.data.DataOrException
import com.example.myapplication.modole.Weather
import com.example.myapplication.network.WeatherApi
import javax.inject.Inject

class WeatherRepastory @Inject constructor(private val api: WeatherApi) {

     suspend fun getWeather(cityQuery: String, units: String): DataOrException<Weather,Boolean,Exception>  {
         val response = try {
             api.getWeather(query = cityQuery,units=units)

         }catch (e:Exception){
             Log.d("Rex", "getWeather: $e")
             return DataOrException(e=e)
         }
         Log.d("INSIDE", "getWeather: $response")

         return DataOrException(data=response)

     }
}
