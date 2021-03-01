package com.pdinc.weatherapp.data.source.local.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pdinc.weatherapp.data.source.local.Entity.DBWeatherForecast
import com.pdinc.weatherapp.data.source.local.Entity.DBweather

@Dao
interface WeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeather(vararg dBweather: DBweather)

    @Query("SELECT * FROM weather_table ORDER BY uniqueId DESC LIMIT 1")
    suspend fun getWeather(): DBweather

    @Query("SELECT * FROM weather_table ORDER BY uniqueId DESC ")
    suspend fun getAllWeather():List<DBweather>

    @Query("DELETE FROM weather_table")
    suspend fun deleteAllWeather()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertForecastWeather(vararg dbWeatherForecast: DBWeatherForecast)

    @Query("SELECT * FROM weather_forecast ORDER BY id")
    suspend fun getAllWeatherForecast():List<DBWeatherForecast>

    @Query("DELETE FROM weather_forecast")
    suspend fun deleteAllWeatherForecast()
}