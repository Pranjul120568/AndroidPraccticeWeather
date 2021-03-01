package com.pdinc.weatherapp.data.source.repo

import com.pdinc.weatherapp.data.models.*
import com.pdinc.weatherapp.data.source.local.WeatherLocalDataSource
import com.pdinc.weatherapp.data.source.remote.WeatherRemoteDataSource
import com.pdinc.weatherapp.mapper.*
import com.pdinc.weatherapp.utils.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WeatherRepositoryImpl(
        private val remoteDataSource: WeatherRemoteDataSource,
        private val localDataSource: WeatherLocalDataSource,
        private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : WeatherRepository {

    override suspend fun getWeather(
            locationModel: LocationModel,
            refresh: Boolean
    ): Result<Weather> =
            withContext(ioDispatcher) {
                if (refresh) {
                    val mapper = WeatherMapperRemote()
                    when (val response = remoteDataSource.getWeather(locationModel)) {
                        is Result.Success -> {
                            if (response.data != null) {
                                Result.Success(mapper.transfromToDomain(response.data))
                            } else {
                                Result.Success(null)
                            }
                        }

                        is Result.Error -> {
                            Result.Error(response.exception)
                        }

                        else -> Result.Loading
                    }
                } else {
                    val mapper = WeatherMapperLocal()
                    val forecast = localDataSource.getWeather()
                    if (forecast != null) {
                        Result.Success(mapper.transfromToDomain(forecast))
                    } else {
                        Result.Success(null)
                    }
                }
            }

    override suspend fun getWeatherForecast(
        cityId: Int,
        refresh: Boolean
    ): Result<List<WeatherForecast>> = withContext(ioDispatcher) {
        if (refresh) {
            val mapper = WeatherForecastMapperRemote()
            when (val response = remoteDataSource.getWeatherForecast(cityId)) {
                is Result.Success -> {
                    if (response.data != null) {
                        Result.Success(mapper.transfromToDomain(response.data))
                    } else {
                        Result.Success(null)
                    }
                }

                is Result.Error -> {
                    Result.Error(response.exception)
                }

                else -> Result.Loading
            }
        } else {
            val mapper = WeatherForecastMapperLocal()
            val forecast = localDataSource.getForecastWeather()
            if (forecast != null) {
                Result.Success(mapper.transfromToDomain(forecast))
            } else {
                Result.Success(null)
            }
        }
    }

    override suspend fun storeWeatherData(weather: Weather) = withContext(ioDispatcher) {
        val mapper = WeatherMapperLocal()
        localDataSource.saveWeather(mapper.transformToDto(weather))
    }

    override suspend fun storeForecastData(forecasts: List<WeatherForecast>) =
            withContext(ioDispatcher) {
                val mapper = WeatherForecastMapperLocal()
                mapper.transformToDto(forecasts).let { listOfDbForecast ->
                    listOfDbForecast.forEach {
                        localDataSource.saveForecastWeather(it)
                    }
                }
            }

    override suspend fun getSearchWeather(location: String): Result<Weather?> =
            withContext(ioDispatcher) {
                val mapper = WeatherMapperRemote()
                return@withContext when
                        (val response = remoteDataSource.getSearchWeather(location)) {
                    is Result.Success -> {
                        if (response.data != null) {
                            Result.Success(mapper.transfromToDomain(response.data))
                        } else {
                            Result.Success(null)
                        }
                    }
                    is Result.Error -> {
                        Result.Error(response.exception)
                    }
                    else -> {
                        Result.Loading
                    }
                }
            }
    override suspend fun deleteWeatherData() = withContext(ioDispatcher) {
        localDataSource.deleteWeather()
    }
    override suspend fun deleteForecastData() {
        localDataSource.deleteWeatherForecast()
    }
}