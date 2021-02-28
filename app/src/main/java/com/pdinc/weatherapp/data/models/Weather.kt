package com.pdinc.weatherapp.data.models

import com.pdinc.weatherapp.data.models.Wind

data class Weather(
    val uId: Int,
    val cityId: Int,
    val name: String,
    val wind: Wind,
    val networkWeatherDescription: List<NetworkWeatherDescription>,
    val networkWeatherCondition: NetworkWeatherCondition
) : java.io.Serializable
