package com.example.myapplication.widget

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.myapplication.navigation.WeatherScreans

import com.example.myapplication.Screans.favourite.FavouriteViewmodule
import com.example.myapplication.modole.Favourite

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun weatherAppBar(
    title:String = "Title",
    icon:ImageVector?=null,
    isMainScrean:Boolean=true,
    elevation: Dp =0.dp,
    favouriteViewmodule: FavouriteViewmodule= hiltViewModel(),
    navController: NavController,
    onAddActionClicked: ()-> Unit={},
    onButtonClicked: () -> Unit={}

){
    val showIt = remember {
        mutableStateOf(false)
    }
    val context= LocalContext.current
    val ShowDialog= remember {
        mutableStateOf(false)
    }
    if (ShowDialog.value){
         ShowSettingDropDownMenu(ShowDialog=ShowDialog,navController=navController)
    }
    TopAppBar(title = {
        Text(text = title,
            color = MaterialTheme.colorScheme.onSecondary,
            style = TextStyle(fontWeight = FontWeight.Bold,
                fontSize = 22.sp)
        , modifier = Modifier.padding(start = 58.dp))
    }, actions = {
                 if (isMainScrean){
                     IconButton(onClick = {
                         onAddActionClicked.invoke()
                     }) {
                         Icon(imageVector = Icons.Default.Search, contentDescription = "search icon")
                     }
                     IconButton(onClick = { ShowDialog.value= ! ShowDialog.value }) {
                         Icon(imageVector = Icons.Rounded.MoreVert, contentDescription = "MoreVert")

                     }

                 }else {
                     Box(){

                     }
                 }

    }, navigationIcon = {
        if (icon != null){
            Icon(imageVector = icon, contentDescription = null ,
                tint = MaterialTheme.colorScheme.onSecondary,
                modifier = Modifier.clickable {
                    onButtonClicked.invoke()
                })
        }
        if (isMainScrean){
            val isAlreadyFavlist=favouriteViewmodule.favList.collectAsState().value.filter { item ->
                (item.city== title.split(",")[0])

            }
            if (isAlreadyFavlist.isNullOrEmpty()){
                Icon(imageVector = Icons.Default.Favorite, contentDescription = "favourite",
                    modifier = Modifier
                        .scale(0.9f)
                        .clickable {
                            val datadlist = title.split(",")

                            favouriteViewmodule.insertFavourites(
                                Favourite(
                                    city = datadlist[0],
                                    country = datadlist[1]
                                )
                            ).run {
                                showIt.value=true
                            }
                        },
                    tint = Color.Red.copy(alpha=0.6f)
                )


            }else {
                showIt.value=false
                Box{ }
                ShowToast( context = context, showIt)
            }
        }

    })

}

@Composable
fun ShowToast(context: Context, showIt: MutableState<Boolean>) {
    if (showIt.value){
        Toast.makeText(context,"Cites Add to favourites",
            Toast.LENGTH_SHORT).show()
    }


}

@Composable
fun ShowSettingDropDownMenu(ShowDialog: MutableState<Boolean>
                            , navController: NavController) {
    var expended by remember {
        mutableStateOf(true)
    }
    val items= listOf("About", "Favourite","Settings")
    Column (modifier = Modifier
        .fillMaxWidth()
        .wrapContentSize(Alignment.TopEnd)
        .absolutePadding(top = 45.dp, right = 25.dp)) {
        DropdownMenu(
            expanded = expended, onDismissRequest = { expended = false },
            modifier = Modifier
                .width(140.dp)
                .background(Color.White)
        ) {
            items.forEachIndexed{ index,text ->
                DropdownMenuItem(text = {
                    Icon(
                        imageVector = when (text) {
                            "About" -> Icons.Default.Info
                            "Favourite" -> Icons.Default.FavoriteBorder
                            else -> Icons.Default.Settings



                        }, contentDescription =null,
                        tint = Color.LightGray,

                        )
                    Text(text = text, modifier = Modifier
                        .clickable {
                            navController.navigate(
                                when (text) {
                                    "About" -> WeatherScreans.Aboutscrean.name
                                    "Favourite" -> WeatherScreans.favouritescrean.name
                                    else -> WeatherScreans.Settingsscrean.name


                                }
                            )
                        }
                        .padding(start = 30.dp),
                        fontWeight = FontWeight.Bold, color = Color.Black,

                    )

                }, onClick = {
                    expended=false
                    ShowDialog.value=true

                })


            }




        }

    }}
