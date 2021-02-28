package com.pdinc.weatherapp.data.source.remote.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WeatherApiClient {
    private val retrofit=Retrofit.Builder()
        .baseUrl(ApiEndPoints.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val retrofitService by lazy {
        retrofit.create(WeatherApiService::class.java)
    }
}