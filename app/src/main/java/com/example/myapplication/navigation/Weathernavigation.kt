package com.example.myapplication.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myapplication.Screans.Main.MainViewModule
import com.example.myapplication.Screans.Main.Mainscrean
import com.example.myapplication.Screans.Search.searchscrean
import com.example.myapplication.Screans.Settings.settingsscrean
import com.example.myapplication.Screans.Weathersplashscrean
import com.example.myapplication.Screans.about.aboutscrean
import com.example.myapplication.Screans.favourite.favouritescrean

@Composable
fun Weathernavigation (){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = WeatherScreans.Splashscrean.name ){
        composable(WeatherScreans.Splashscrean.name){
            Weathersplashscrean(navController=navController)
        }
        val route=WeatherScreans.Mainscrean.name
        composable("$route/{city}",
            arguments = listOf(
                navArgument(name = "city"){
                    type= NavType.StringType
                })){  navBack ->
            navBack.arguments?.getString("city").let { city ->
                val mainViewModule= hiltViewModel<MainViewModule>()
                Mainscrean(navController=navController,mainViewModule,
                    city=city)


            }
        }
        composable(WeatherScreans.Searchscrean.name){
            searchscrean(navController=navController)
        }
        composable(WeatherScreans.favouritescrean.name){
            favouritescrean(navController=navController)
        }
        composable(WeatherScreans.Aboutscrean.name){
            aboutscrean(navController=navController)
        }
        composable(WeatherScreans.Settingsscrean.name){
            settingsscrean(navController=navController)
        }




    }

}