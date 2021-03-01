package com.pdinc.weatherapp.mapper

import com.pdinc.weatherapp.data.models.NetworkWeatherForecast
import com.pdinc.weatherapp.data.models.WeatherForecast

class WeatherForecastMapperRemote {
    class WeatherForecastMapperRemote :
        BaseMapper<List<NetworkWeatherForecast>, List<WeatherForecast>> {

        override fun transfromToDomain(type: List<NetworkWeatherForecast>): List<WeatherForecast> {
            return type.map { networkWeatherForecast ->
                WeatherForecast(
                    networkWeatherForecast.id,
                    networkWeatherForecast.date,
                    networkWeatherForecast.wind,
                    networkWeatherForecast.networkWeatherDescription,
                    networkWeatherForecast.networkWeatherCondition
                )
            }

        }
        override fun transformToDto(type: List<WeatherForecast>): List<NetworkWeatherForecast> {
            return type.map { weatherForecast ->
                NetworkWeatherForecast(
                    weatherForecast.uID,
                    weatherForecast.date,
                    weatherForecast.wind,
                    weatherForecast.networkWeatherDescription,
                    weatherForecast.networkWeatherCondition
                )
            }
        }
    }
}