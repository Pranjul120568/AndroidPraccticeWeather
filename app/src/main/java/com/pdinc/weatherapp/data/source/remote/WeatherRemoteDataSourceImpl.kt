package com.pdinc.weatherapp.data.source.remote

import com.pdinc.weatherapp.data.models.LocationModel
import com.pdinc.weatherapp.data.models.NetworkWeather
import com.pdinc.weatherapp.data.models.NetworkWeatherForecast
import com.pdinc.weatherapp.utils.Result

class WeatherRemoteDataSourceImpl():WeatherRemoteDataSource {
    override suspend fun getWeather(locationModel: LocationModel): Result<NetworkWeather> {
        TODO("Not yet implemented")
    }

    override suspend fun getWeatherForecast(cityId: Int): Result<List<NetworkWeatherForecast>> {
        TODO("Not yet implemented")
    }

    override suspend fun getSearchWeather(query: String): Result<NetworkWeather> {
        TODO("Not yet implemented")
    }

}