package com.pdinc.weatherapp.data.models

import com.codingblocks.weatherapp.data.model.Wind
import com.google.gson.annotations.SerializedName

data class NetworkWeatherForecast(

    val id: Int,

    @SerializedName("dt_txt")
    val date: String,

    val wind: Wind,

    @SerializedName("weather")
    val networkWeatherDescription: List<NetworkWeatherDescription>,

    @SerializedName("main")
    val networkWeatherCondition: NetworkWeatherCondition
)
