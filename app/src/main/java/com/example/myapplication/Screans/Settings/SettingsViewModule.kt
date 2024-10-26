package com.example.myapplication.Screans.Settings

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.modole.Favourite
import com.example.myapplication.modole.Unit
import com.example.myapplication.repostory.WeatherDbRebasitory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModule @Inject constructor(private val repestory: WeatherDbRebasitory)
    : ViewModel(){
    private val _unitList= MutableStateFlow<List<Unit>>(emptyList())
    val unitList=_unitList.asStateFlow()
    init {

        viewModelScope.launch(Dispatchers.IO) {
            repestory.getUnits().distinctUntilChanged().collect{listOfUnits ->
                if (listOfUnits.isNullOrEmpty()){
                    Log.d("TAG", ": Empty Unit")
                }else{
                    _unitList.value=listOfUnits
                  //  Log.d("Favs", ": ${favList.value}")
                }

            }

        }
    }
    fun insertUnit(unit: Unit) = viewModelScope.launch {

        repestory.insertUnit(unit)
    }
    fun updateUnit(unit: Unit) = viewModelScope.launch {
        repestory.updateUnit(unit)
    }
    fun deleteUnit(unit: Unit) = viewModelScope.launch {
        repestory.deleteUnits(unit)
    }
    fun deleteAllUnit() = viewModelScope.launch {
        repestory.deleteAllUnits()
    }


}
