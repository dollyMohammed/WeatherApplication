package com.example.myapplication.Screans.about

import android.annotation.SuppressLint
import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.example.myapplication.R
import com.example.myapplication.widget.weatherAppBar

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun aboutscrean(navController: NavController){
    Scaffold (topBar = {
        weatherAppBar(title = "about",
            icon = Icons.Default.ArrowBack,
              false,
            navController = navController){
            navController.popBackStack()
        }
    }) {
        Surface (modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()){
            Column (horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center){
                Text(text = stringResource(id = R.string.about_app),
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodyLarge)
                Text(text = stringResource(id = R.string.api_used),
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodyLarge)


            }

        }

    }
}