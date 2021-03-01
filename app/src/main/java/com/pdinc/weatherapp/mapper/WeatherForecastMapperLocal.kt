package com.pdinc.weatherapp.mapper

import com.pdinc.weatherapp.data.models.WeatherForecast
import com.pdinc.weatherapp.data.source.local.Entity.DBWeatherForecast

class WeatherForecastMapperLocal :BaseMapper<List<DBWeatherForecast>,List<WeatherForecast>> {
    override fun transfromToDomain(type: List<DBWeatherForecast>): List<WeatherForecast> {
        return type.map {dbWeatherForecast ->
            WeatherForecast(
                dbWeatherForecast.id,
                dbWeatherForecast.date,
                dbWeatherForecast.wind,
                dbWeatherForecast.networkWeatherDescriptions,
                dbWeatherForecast.networkWeatherCondition
            )

        }
    }

    override fun transformToDto(type: List<WeatherForecast>): List<DBWeatherForecast> {
        return type.map {weatherForecast ->
            DBWeatherForecast(
                weatherForecast.uID,
                weatherForecast.date,
                weatherForecast.wind,
                weatherForecast.networkWeatherDescription,
                weatherForecast.networkWeatherCondition
            )

        }
    }
}