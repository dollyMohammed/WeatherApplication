package com.example.myapplication.Screans.favourite

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.modole.Favourite
import com.example.myapplication.repostory.WeatherDbRebasitory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteViewmodule @Inject constructor(private val repestory:WeatherDbRebasitory)
    :ViewModel(){
        private val _favlist=MutableStateFlow<List<Favourite>>(emptyList())
    val favList=_favlist.asStateFlow()
    init {

        viewModelScope.launch(Dispatchers.IO) {
            repestory.getFavourites().distinctUntilChanged().collect{listOfFavs ->
                if (listOfFavs.isNullOrEmpty()){
                    Log.d("TAG", ": Empty Davs")
                }else{
                    _favlist.value=listOfFavs
                    Log.d("Favs", ": ${favList.value}")
                }

            }

        }
    }
    fun insertFavourites(favourite: Favourite) = viewModelScope.launch {

        repestory.insertFavourites(favourite)
    }
    fun updateFavourite(favourite: Favourite) = viewModelScope.launch {
        repestory.updateFavourite(favourite)
    }
    fun deleteFavourite(favourite: Favourite) = viewModelScope.launch {
        repestory.deleteFavourite(favourite)
    }

}