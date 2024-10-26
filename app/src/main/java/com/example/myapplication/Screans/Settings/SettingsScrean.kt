package com.example.myapplication.Screans.Settings

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.myapplication.modole.Unit
import com.example.myapplication.widget.weatherAppBar
import java.security.AllPermission

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun settingsscrean(navController: NavController,
                   settingsViewModule: SettingsViewModule= hiltViewModel()
){
  var uniteToggleState by remember {
      mutableStateOf(false)
  }
    val measurmentunits= listOf("Imperial(F)","metric(C)")
    val choisFromDB=settingsViewModule.unitList.collectAsState().value
    val choisDef by remember {
        mutableStateOf(0)
    }
    val defaultChoise= if(choisFromDB.isNullOrEmpty()) measurmentunits[0] else choisFromDB[0].unit
    var choisState by remember {
       mutableStateOf(defaultChoise)
    }

    Scaffold (topBar = {
    weatherAppBar(
        title = "Settings",
        icon = Icons.Default.ArrowBack,
        false,
        navController = navController){
        navController.popBackStack()
    }
}){
    Surface (modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()){
        Column (verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally){
            Text(text = "Change Units of Measurment", modifier = Modifier.padding(bottom = 15.dp))
            IconToggleButton(checked = !uniteToggleState, onCheckedChange = {
                uniteToggleState=!it
                if (uniteToggleState) {
                    choisState= "Imperial(F)"
                }else{
                    choisState="metric(C)"
                }
            }, modifier = Modifier
                .fillMaxWidth(0.5f)
                .clip(shape = RectangleShape)
                .padding(5.dp)
                .background(Color.Magenta.copy(alpha = 0.4f))) {
                Text(text = if (uniteToggleState) " Fahrenhait F" else "Celsies C")
                
                
            }

        }
        Button(onClick = {
                         settingsViewModule.deleteAllUnit()
            settingsViewModule.insertUnit(Unit(unit = choisState))
        },
            modifier = Modifier.padding(3.dp),
            shape = RoundedCornerShape(34.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFEFBE42)
            )
        ) {
            Text(text = "Save", modifier = Modifier.padding(4.dp), color = Color.White, fontSize = 17.sp)


        }

    }

}
}