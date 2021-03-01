package com.pdinc.weatherapp.mapper

import com.pdinc.weatherapp.data.models.Weather
import com.pdinc.weatherapp.data.source.local.Entity.DBweather


class WeatherMapperLocal : BaseMapper<DBweather, Weather> {

    override fun transfromToDomain(type: DBweather): Weather = Weather(
        uId = type.uId,
        cityId = type.cityId,
        name = type.cityName,
        wind = type.wind,
        networkWeatherDescription = type.networkWeatherDescription,
        networkWeatherCondition = type.networkWeatherCondition
    )
    override fun transformToDto(type: Weather): DBweather = DBweather(
        uId = type.uId,
        cityId = type.cityId,
        cityName = type.name,
        wind = type.wind,
        networkWeatherDescription = type.networkWeatherDescription,
        networkWeatherCondition = type.networkWeatherCondition
    )
}