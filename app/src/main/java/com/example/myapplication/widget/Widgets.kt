package com.example.myapplication.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.myapplication.R
import com.example.myapplication.modole.WeatherObject
import com.example.myapplication.utils.formatDecimals
import com.example.myapplication.utils.formateDataTime
import com.example.myapplication.utils.formateDate

@Composable
fun WeatherDetailRow(weather: WeatherObject) {
    val imageUrl = "https://openweathermap.org/img/wn/${weather.weather[0].icon}.png"
    Surface (modifier = Modifier
        .padding(2.dp)
        .fillMaxWidth(),
        shape = CircleShape.copy(topEnd = CornerSize(6.dp)),
        color = Color.White
    ){
        Row (modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween){
            Text(text = formateDate(weather.dt).split(",")[0], modifier = Modifier.padding(5.dp),
                color = Color.Black
            )
            WeatherStateimage(imageUrl = imageUrl)
            Surface (modifier = Modifier.padding(0.dp),
                shape = CircleShape,
                color= Color(0xFFFFC400)
            ){
                Text(text = weather.weather[0].description,
                    modifier = Modifier.padding(4.dp),
                    style = MaterialTheme.typography.bodyMedium)

            }
            Text(text = buildAnnotatedString {
                withStyle(style = SpanStyle(
                    color = Color.Blue.copy(alpha = 0.7f),
                    fontWeight = FontWeight.SemiBold

                )
                ){
                    append(formatDecimals(weather.temp.max) )
                }
                withStyle(style = SpanStyle(
                    color = Color.LightGray,
                    fontWeight = FontWeight.SemiBold

                )
                ){
                    append(formatDecimals(weather.temp.min) )
                }

            })

        }

    }


}

@Composable
fun SunsetSunriseRow(weather: WeatherObject) {
    Row (modifier = Modifier
        .fillMaxWidth()
        .padding(top = 15.dp, bottom = 6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween){
        Row {
            Image(painter = painterResource(id = R.drawable.sunrise), contentDescription = "sunrise",
                modifier = Modifier.size(25.dp))
            Text(text = formateDataTime(weather.sunrise),
                style = MaterialTheme.typography.bodyMedium)


        }
        Row (){
            Text(text = formateDataTime(weather.sunset),
                style = MaterialTheme.typography.bodyMedium)

            Image(painter = painterResource(id = R.drawable.sunset), contentDescription = "sunrise",
                modifier = Modifier.size(30.dp))

        }


    }



}

@Composable
fun HumidtyWindPressureRow(weather: WeatherObject,isImperial:Boolean) {
    Row (modifier = Modifier
        .fillMaxWidth()
        .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween){
        Row (modifier = Modifier.padding(4.dp)){
            Icon(painter = painterResource(id = R.drawable.humidity), contentDescription = "humudity",
                modifier = Modifier.size(20.dp),
            )
            Text(text = "${weather.humidity}%", style = MaterialTheme.typography.bodyMedium)
        }
        Row (modifier = Modifier.padding(4.dp)){
            Icon(painter = painterResource(id = R.drawable.pressure), contentDescription = "humudity",
                modifier = Modifier.size(20.dp),
            )
            Text(text = "${weather.pressure}psi", style = MaterialTheme.typography.bodyMedium)
        }

        Row (modifier = Modifier.padding(4.dp)){
            Icon(painter = painterResource(id = R.drawable.wind), contentDescription = "humudity",
                modifier = Modifier.size(20.dp),
            )
            Text(text = "${formatDecimals( weather.speed)}mpm", style = MaterialTheme.typography.bodyMedium)
        }



    }

}

@Composable
fun WeatherStateimage(imageUrl: String) {
    Image(painter = rememberImagePainter(imageUrl), contentDescription = "image",
        modifier = Modifier.size(80.dp))

}
