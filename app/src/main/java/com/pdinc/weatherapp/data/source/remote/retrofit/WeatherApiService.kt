package com.pdinc.weatherapp.data.source.remote.retrofit

import com.pdinc.weatherapp.data.models.NetworkWeather
import com.pdinc.weatherapp.data.models.NetworkWeatherForecastResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("data/2.5/weather")
    suspend fun getSpecificWeather(
        @Query("q") location: String,
        @Query("appid") apiKey: String
    ):Response<NetworkWeather>

    @GET("data/2.5/weather")
    suspend fun getCurrentWeather(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("appid") apiKey: String
    ):Response<NetworkWeather>

    @GET("data/2.5/weather")
    suspend fun getWeatherForecast(
        @Query("id") cityId: Int,
        @Query("appid") apiKey: String
    ):Response<NetworkWeatherForecastResponse>
}