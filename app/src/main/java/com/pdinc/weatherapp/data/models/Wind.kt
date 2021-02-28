package com.pdinc.weatherapp.data.models

import java.io.Serializable

data class Wind(
    val speed: Double,
    val deg: Int
) : Serializable
