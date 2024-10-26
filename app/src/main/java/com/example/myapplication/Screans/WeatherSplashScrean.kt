package com.example.myapplication.Screans

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.R
import com.example.myapplication.navigation.WeatherScreans
import com.example.myapplication.weatherapp
import kotlinx.coroutines.delay

@Composable
fun Weathersplashscrean(navController: NavController){
   val defaultCity = "Cairo"
    val scale = remember {
        androidx.compose.animation.core.Animatable(0f)
    }
    LaunchedEffect(key1 = true, block = {
        scale.animateTo(targetValue = 0.9f, animationSpec = tween(
            durationMillis = 800,
            easing = {
                OvershootInterpolator(8f)
                    .getInterpolation(it)
            }
        ))
        delay(2000L)
        navController.navigate(WeatherScreans.Mainscrean.name +"/$defaultCity")
    })
        Surface (color = Color.White,
            modifier = Modifier
                .size(330.dp)
                .padding(15.dp)
                .scale(scale.value),
            shape = CircleShape,
            border = BorderStroke(width = 2.dp, color = Color.LightGray)
        ){
            Column (modifier = Modifier.padding(1.dp) ,
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally){
                Image(painter = painterResource(id = R.drawable.img), contentDescription = ""
                , modifier = Modifier.size(95.dp),
                    contentScale = ContentScale.Fit)
                Text(text = "Find the sun ?" ,
                    color = Color.LightGray,
                    fontWeight = FontWeight.SemiBold
                )



                



        }


    }
}