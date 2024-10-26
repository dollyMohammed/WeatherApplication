package com.example.myapplication.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.modole.Favourite
import com.example.myapplication.modole.Unit

@Database(entities = [Favourite::class,Unit::class], version = 2, exportSchema = false)
abstract class  WeatherDatabase: RoomDatabase() {
    abstract fun weatherDao():WeatherDao
}