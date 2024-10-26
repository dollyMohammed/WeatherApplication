package com.example.myapplication.Screans.Main

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.myapplication.Screans.Settings.SettingsViewModule
import com.example.myapplication.data.DataOrException
import com.example.myapplication.modole.Weather
import com.example.myapplication.modole.WeatherObject
import com.example.myapplication.navigation.WeatherScreans
import com.example.myapplication.utils.formatDecimals
import com.example.myapplication.utils.formateDate
import com.example.myapplication.widget.HumidtyWindPressureRow
import com.example.myapplication.widget.SunsetSunriseRow
import com.example.myapplication.widget.WeatherDetailRow
import com.example.myapplication.widget.WeatherStateimage
import com.example.myapplication.widget.weatherAppBar

@Composable
fun Mainscrean(
    navController: NavController,
    mainViewModule: MainViewModule = hiltViewModel(),
    settingsViewModule: SettingsViewModule= hiltViewModel(),
    city: String?
) {
    Surface (color = Color.White,
        modifier = Modifier.fillMaxSize()){


    val curCity: String = if (city!!.isBlank()) "Seattle" else city
    // Log.d("TAG", "Mainscrean: $city")
     val uniteFromDb = settingsViewModule.unitList.collectAsState().value
    var unit by remember {
        mutableStateOf("imperial")
    }
    var isImperial by remember {
        mutableStateOf(false)
    }
     if (!uniteFromDb.isNullOrEmpty()){
     unit=uniteFromDb[0].unit.split("")[0].lowercase()
    isImperial = unit == "imperial"
    val weatherData = produceState<DataOrException<Weather, Boolean, Exception>>(
        initialValue = DataOrException(loading = true)
    ) {
        value = mainViewModule.getWeatherData(
            city = curCity,
            units = unit
        )
    }.value

    if (weatherData.loading == true) {
        CircularProgressIndicator()
    } else if (weatherData.data != null) {
        //Text(text = "new...")
        //Text(text = "mainscrean ${weatherData.data!!.toString()}")
        Mainscaffold(weather = weatherData.data!!, navController,isImperial=isImperial)


    } else {
        Text(text = "Failed.....")
    }
}


    }


}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Mainscaffold(weather: Weather, navController: NavController, isImperial: Boolean) {
    Scaffold (topBar = {
        weatherAppBar(navController = navController,
             onAddActionClicked={ navController.navigate(WeatherScreans.Searchscrean.name) },

            title = weather.city.name + ", ${weather.city.country}", elevation = 5.dp,
            icon = Icons.Default.ArrowBack,
            ){
            Log.d("tag", "Mainscaffold: Button Clicked")
        }
    }){


    }
    Column (modifier = Modifier.padding(top = 50.dp)){
        MainContent(data=weather,isImperial=isImperial)



    }





}

@Composable
fun MainContent(data: Weather, isImperial: Boolean) {
    val weatheritems=data.list[0]

    val imageUrl = "https://openweathermap.org/img/wn/${weatheritems.weather[0].icon}.png"
Column {
    //Text(text = data.city.name)
    Column (modifier = Modifier.padding(4.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){
        Text(
            text = formateDate( data.list[0].dt),
            modifier = Modifier.padding(start = 100.dp),
            color = MaterialTheme.colorScheme.onSecondary,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.height(10.dp))
        Surface (modifier = Modifier
            .padding(start = 100.dp)
            .size(200.dp),
            shape = CircleShape,
            color = Color(0xFFFFC400)
        ){
            Column (verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally){
                WeatherStateimage(imageUrl = imageUrl)
                Text(text = formatDecimals(data.list[0].temp.day), fontWeight = FontWeight.ExtraBold,
                    style = MaterialTheme.typography.bodyMedium)
                Text(text = weatheritems.weather[0].main, fontStyle = FontStyle.Italic)

            }

        }

    }



}
    HumidtyWindPressureRow(weather = data.list[0],isImperial=isImperial)
    Divider()
    SunsetSunriseRow(weather=data.list[0])
    Text(text = "This Weak", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(start = 150.dp))
    Surface (modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(),
        color = Color(0xFFEEF1EF),
        shape = RoundedCornerShape(size = 14.dp)
    ){
        LazyColumn(modifier = Modifier.padding(2.dp),
            contentPadding = PaddingValues(1.dp)
        ){
            items(items=data.list){item:WeatherObject ->
                WeatherDetailRow(weather=item)

            }

        }

    }

}

