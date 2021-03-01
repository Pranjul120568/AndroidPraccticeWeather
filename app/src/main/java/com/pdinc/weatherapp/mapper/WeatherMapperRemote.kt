package com.pdinc.weatherapp.mapper

import com.pdinc.weatherapp.data.models.NetworkWeather
import com.pdinc.weatherapp.data.models.Weather


class WeatherMapperRemote : BaseMapper<NetworkWeather, Weather> {
    override fun transfromToDomain(type: NetworkWeather): Weather = Weather(
        uId = type.uId,
        cityId = type.cityId,
        name = type.name,
        wind = type.wind,
        networkWeatherDescription = type.networkWeatherDescriptions,
        networkWeatherCondition = type.networkWeatherCondition
    )

    override fun transformToDto(type: Weather): NetworkWeather = NetworkWeather(
        uId = type.uId,
        cityId = type.cityId,
        name = type.name,
        wind = type.wind,
        networkWeatherDescriptions = type.networkWeatherDescription,
        networkWeatherCondition = type.networkWeatherCondition
    )


}