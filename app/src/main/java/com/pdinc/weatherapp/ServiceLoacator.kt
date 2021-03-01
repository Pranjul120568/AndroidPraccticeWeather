package com.pdinc.weatherapp

import android.content.Context
import androidx.room.Room
import com.pdinc.weatherapp.data.source.local.WeatherDatabse
import com.pdinc.weatherapp.data.source.local.WeatherLocalDataSource
import com.pdinc.weatherapp.data.source.local.WeatherLocalDataSourceImpl
import com.pdinc.weatherapp.data.source.remote.WeatherRemoteDataSourceImpl
import com.pdinc.weatherapp.data.source.repo.WeatherRepository
import com.pdinc.weatherapp.data.source.repo.WeatherRepositoryImpl

object ServiceLocator {

    private var database: WeatherDatabse? = null

    @Volatile
    var weatherRepository: WeatherRepository? = null

    fun provideWeatherRepository(context: Context): WeatherRepository {
        synchronized(this) {
            return weatherRepository ?: createWeatherRepository(context)
        }
    }

    private fun createWeatherRepository(context: Context): WeatherRepository {
        val newRepo = WeatherRepositoryImpl(
                WeatherRemoteDataSourceImpl(),
                createLocalWeatherSource(context)
        )
        weatherRepository = newRepo
        return newRepo
    }

    private fun createLocalWeatherSource(context: Context): WeatherLocalDataSource {
        val database = database ?: createDatabase(context)
        return WeatherLocalDataSourceImpl(database.weatherDao)
    }

    private fun createDatabase(context: Context): WeatherDatabse {
        val result = Room.databaseBuilder(
                context.applicationContext,
                WeatherDatabse::class.java,
                "Weather.db"
        ).build()
        database = result
        return result
    }
}
