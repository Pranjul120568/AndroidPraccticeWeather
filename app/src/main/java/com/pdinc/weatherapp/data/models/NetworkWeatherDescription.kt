package com.pdinc.weatherapp.data.models

import java.io.Serializable

data class NetworkWeatherDescription(
    val id: Long,
    val main: String?,
    val description: String?,
    val icon: String?
) : Serializable
