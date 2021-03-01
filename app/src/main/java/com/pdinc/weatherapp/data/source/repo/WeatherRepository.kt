package com.pdinc.weatherapp.data.source.repo

import com.pdinc.weatherapp.data.models.*
import com.pdinc.weatherapp.utils.Result

interface WeatherRepository {
    suspend fun getWeather(locationModel: LocationModel, refresh:Boolean) : Result<Weather?>

    suspend fun getWeatherForecast(cityId:Int,refresh: Boolean) : Result<List<WeatherForecast>>

    suspend fun getSearchWeather(location: String) : Result<Weather?>

    suspend fun storeWeatherData(weather: Weather)

    suspend fun storeForecastData(forecasts: List<WeatherForecast>)

    suspend fun deleteWeatherData()

    suspend fun deleteForecastData()

}