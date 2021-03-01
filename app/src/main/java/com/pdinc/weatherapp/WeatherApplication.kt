package com.pdinc.weatherapp

import android.app.Application
import com.pdinc.weatherapp.data.source.repo.WeatherRepository

class WeatherApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }

    val weatherRepository: WeatherRepository
        get() = ServiceLocator.provideWeatherRepository(this)

}