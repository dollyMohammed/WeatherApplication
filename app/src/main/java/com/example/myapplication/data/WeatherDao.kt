package com.example.myapplication.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplication.modole.Favourite
import androidx.room.OnConflictStrategy
import androidx.room.Update
import com.example.myapplication.modole.Unit
import kotlinx.coroutines.flow.Flow
import retrofit2.http.DELETE

@Dao
interface WeatherDao {
    @Query("SELECT * from fav_tbl")
    fun getFavourites(): Flow<List<Favourite>>

    @Query("SELECT * from fav_tbl where city=:city")
    suspend fun getFavByed(city:String) : Favourite

    @Insert(onConflict = OnConflictStrategy.REPLACE )
    suspend fun insertFavourite(favourite: Favourite)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateFavourite(favourite: Favourite)


    @Query("DElETE from fav_tbl")
    suspend fun deleteAllFavourite()

    @Delete
    suspend fun deleteFavourite(favourite: Favourite)

    @Query("SELECT * from settings_tbl")
    fun getUnits(): Flow<List<Unit>>

    @Insert(onConflict = OnConflictStrategy.REPLACE )
    suspend fun insertUnit(unit: Unit)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateUnit(unit: Unit)

    @Query("DElETE from settings_tbl")
    suspend fun deletAllunits()


    @Delete
    suspend fun deleteunit(unit: Unit)


}