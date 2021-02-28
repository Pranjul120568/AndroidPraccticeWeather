package com.pdinc.weatherapp.data.models

import com.pdinc.weatherapp.data.models.Wind

data class WeatherForecast(
    val uID: Int,

    val date: String,

    val wind: Wind,

    val networkWeatherDescription: List<NetworkWeatherDescription>,

    val networkWeatherCondition: NetworkWeatherCondition
)
