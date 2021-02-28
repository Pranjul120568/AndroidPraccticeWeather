package com.pdinc.weatherapp.data.models

import com.codingblocks.weatherapp.data.model.NetworkWeatherCondition
import com.codingblocks.weatherapp.data.model.NetworkWeatherDescription
import com.codingblocks.weatherapp.data.model.Wind
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
