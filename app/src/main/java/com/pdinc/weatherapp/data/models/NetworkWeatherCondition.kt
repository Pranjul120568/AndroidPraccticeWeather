package com.pdinc.weatherapp.data.models

import java.io.Serializable

data class NetworkWeatherCondition(
    var temp: Double,
    val pressure: Double,
    val humidity: Double
) : Serializable
