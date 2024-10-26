package com.example.myapplication.di

import android.content.Context
import androidx.room.Room
import com.example.myapplication.data.WeatherDao
import com.example.myapplication.data.WeatherDatabase
import com.example.myapplication.network.WeatherApi
import com.example.myapplication.utils.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModuale {

    @Singleton
    @Provides
    fun provideWeatherDoa(weatherDatabase: WeatherDatabase):WeatherDao=
        weatherDatabase.weatherDao()

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context):WeatherDatabase
    = Room.databaseBuilder(
        context,
        WeatherDatabase::class.java,
        "weather_database").fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton

    fun provideOpenWeatherApi():WeatherApi{
        return Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)
    }
}