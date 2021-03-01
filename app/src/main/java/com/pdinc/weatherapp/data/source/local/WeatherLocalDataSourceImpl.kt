package com.pdinc.weatherapp.data.source.local

import com.pdinc.weatherapp.data.source.local.Dao.WeatherDao
import com.pdinc.weatherapp.data.source.local.Entity.DBWeatherForecast
import com.pdinc.weatherapp.data.source.local.Entity.DBweather
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WeatherLocalDataSourceImpl(
    private val weatherDao:WeatherDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
):WeatherLocalDataSource {
    override suspend fun getWeather(): DBweather? = withContext(ioDispatcher) {
        return@withContext weatherDao.getWeather()
    }

    override suspend fun saveWeather(weather: DBweather) = withContext(ioDispatcher) {
        weatherDao.insertWeather(weather)
    }


    override suspend fun deleteWeather() = withContext(ioDispatcher){
        weatherDao.deleteAllWeather()
    }

    override suspend fun getForecastWeather(): List<DBWeatherForecast> = withContext(ioDispatcher) {
        return@withContext weatherDao.getAllWeatherForecast()
    }

    override suspend fun saveForecastWeather(weatherForecast: DBWeatherForecast) = withContext(ioDispatcher){
        weatherDao.insertForecastWeather(weatherForecast)
    }

    override suspend fun deleteWeatherForecast()= withContext(ioDispatcher) {
        weatherDao.deleteAllWeatherForecast()
    }
}