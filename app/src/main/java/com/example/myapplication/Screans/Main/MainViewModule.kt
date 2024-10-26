package com.example.myapplication.Screans.Main

import androidx.lifecycle.ViewModel
import com.example.myapplication.data.DataOrException
import com.example.myapplication.modole.Weather
import com.example.myapplication.repostory.WeatherRepastory
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModule @Inject constructor(private val repostory:WeatherRepastory)
    :ViewModel(){

    suspend fun getWeatherData(city: String, units: String):
            DataOrException<Weather, Boolean, Exception> {
        return repostory.getWeather(cityQuery = city,units=units)
       /* val result = DataOrException<Weather, Boolean, Exception>(loading = true)
        try {
            result.data = repostory.getWeather(cityQuery = city).data
            if (result.data == null) {
                result.loading = false
                throw Exception("No weather data available")
            } else {
                result.loading = false
            }
        } catch (e: Exception) {
            result.e = e
            result.loading = false
        }
        return result*/
    }

       /* suspend fun getWeatherData(city:String)
        :DataOrException<Weather,Boolean,Exception>{
            return repostory.getWeather(cityQuery = city)
        }*/
       /* val data:MutableState<DataOrException<Weather,Boolean,Exception>>
        = mutableStateOf(DataOrException(null,true,Exception("")))

    init {
        loadWeather()
    }

    private fun loadWeather() {
        getWeather("Settle")
    }

    private fun getWeather(city: String) {
        viewModelScope.launch {
            if (city.isEmpty()) return@launch
            data.value.loading=true
            data.value=repostory.getWeather(cityQuery = city)
            if (data.value.data.toString().isNotEmpty()) data.value.loading=false
        }
        Log.d("Get", "getWeather: ${data.value.data.toString()}")

    }*/


}

