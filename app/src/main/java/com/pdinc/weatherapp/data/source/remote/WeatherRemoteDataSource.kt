package com.pdinc.weatherapp.data.source.remote

import com.pdinc.weatherapp.data.models.LocationModel
import com.pdinc.weatherapp.data.models.NetworkWeather
import com.pdinc.weatherapp.data.models.NetworkWeatherForecast
import com.pdinc.weatherapp.utils.Result

interface WeatherRemoteDataSource {
    suspend fun getWeather(locationModel: LocationModel) : Result<NetworkWeather>

    suspend fun getWeatherForecast(cityId:Int) : Result<List<NetworkWeatherForecast>>

    suspend fun getSearchWeather(query: String) : Result<NetworkWeather>


}