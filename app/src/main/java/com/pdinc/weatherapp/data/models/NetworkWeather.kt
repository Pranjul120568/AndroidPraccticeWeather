package com.pdinc.weatherapp.data.models

import com.pdinc.weatherapp.data.models.NetworkWeatherCondition
import com.pdinc.weatherapp.data.models.NetworkWeatherDescription
import com.pdinc.weatherapp.data.models.Wind
import com.google.gson.annotations.SerializedName


data class NetworkWeather(

    val uId: Int,

    @SerializedName("id")
    val cityId: Int,

    val name: String,

    val wind: Wind,

    @SerializedName("weather")
    val networkWeatherDescriptions: List<NetworkWeatherDescription>,

    @SerializedName("main")
    val networkWeatherCondition: NetworkWeatherCondition
)
