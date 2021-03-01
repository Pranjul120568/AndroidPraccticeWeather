package com.pdinc.weatherapp.data.source.local

import com.pdinc.weatherapp.data.source.local.Entity.DBWeatherForecast
import com.pdinc.weatherapp.data.source.local.Entity.DBweather


interface WeatherLocalDataSource {
suspend fun getWeather():DBweather?
suspend fun saveWeather(weather: DBweather)
suspend fun deleteWeather()
suspend fun getForecastWeather():List<DBWeatherForecast>
suspend fun saveForecastWeather(weatherForecast: DBWeatherForecast)
suspend fun deleteWeatherForecast()
}