package com.example.myapplication.Screans.Search

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.navigation.WeatherScreans
import com.example.myapplication.widget.weatherAppBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun searchscrean(navController: NavController){
    Scaffold(topBar = {
        weatherAppBar(
           title = "Search" ,
            navController = navController,
            icon = Icons.Default.ArrowBack,
            isMainScrean = false){
            navController.popBackStack()
        }

    }) {
        Surface {
            Text(text = "=========")
            Column (verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally){
                searchBar(modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally)){ mCity->
                    navController.navigate(WeatherScreans.Mainscrean.name +"/$mCity")
                }
            }
        }

    }
}
@Composable
fun searchBar(modifier: Modifier=Modifier,
    onsearch : (String) -> Unit={}
){
    val searchQueryState= rememberSaveable {
        mutableStateOf("")

    }
val keyboardcontroller= LocalSoftwareKeyboardController.current
    val valid= remember(searchQueryState.value) {
        searchQueryState.value.trim().isNotEmpty()


    }
    Column {
        CommonTextField(
            valueState = searchQueryState,
            placeholder= "Search",
            onAction= KeyboardActions{
                if (!valid) return@KeyboardActions
                onsearch(searchQueryState.value.trim())
                searchQueryState.value=""
                keyboardcontroller?.hide()

            }
        )
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommonTextField(valueState: MutableState<String>,
                    keyboardType:KeyboardType=KeyboardType.Text,
                    imeAction:ImeAction=ImeAction.Next,
                    placeholder: String,
                    onAction: KeyboardActions=KeyboardActions.Default
) {
    OutlinedTextField(value = valueState.value,
        onValueChange = {valueState.value=it},
        label = { Text(text = placeholder) },
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
        keyboardActions = onAction,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Blue,
            cursorColor = Color.Black
        ),
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp, top = 70.dp)








        )

}
