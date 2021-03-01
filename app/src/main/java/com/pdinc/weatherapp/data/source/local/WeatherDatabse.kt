package com.pdinc.weatherapp.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.pdinc.weatherapp.data.source.local.Dao.WeatherDao
import com.pdinc.weatherapp.data.source.local.Entity.DBWeatherForecast
import com.pdinc.weatherapp.data.source.local.Entity.DBweather

@Database(entities = [DBweather::class,DBWeatherForecast::class], version=1)
@TypeConverters(ListNetworkWeatherDescriptionConverter::class)
abstract class WeatherDatabse : RoomDatabase() {
abstract val weatherDao: WeatherDao
}