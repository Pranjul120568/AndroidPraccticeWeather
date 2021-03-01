package com.pdinc.weatherapp.data.source.remote

import com.pdinc.weatherapp.data.models.LocationModel
import com.pdinc.weatherapp.data.models.NetworkWeather
import com.pdinc.weatherapp.data.models.NetworkWeatherForecast
import com.pdinc.weatherapp.data.source.remote.retrofit.WeatherApiClient
import com.pdinc.weatherapp.data.source.remote.retrofit.WeatherApiService
import com.pdinc.weatherapp.utils.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class WeatherRemoteDataSourceImpl():WeatherRemoteDataSource {
    private val ioDispatcher:CoroutineDispatcher=Dispatchers.IO
    private val retrofitClient:WeatherApiService=WeatherApiClient.retrofitService
    override suspend fun getWeather(locationModel: LocationModel): Result<NetworkWeather> =
        withContext(ioDispatcher){
            return@withContext try {
                val result=retrofitClient.getCurrentWeather(
                    locationModel.latitude,locationModel.longitude,com.pdinc.weatherapp.BuildConfig.API_KEY)
                if (result.isSuccessful){
                    val networkWeather=result.body()
                    Result.Success(networkWeather)
                }else{
                    Result.Success(null)
                }
            }catch (exception:Exception){
                Result.Error(exception)
            }
        }

    override suspend fun getWeatherForecast(cityId: Int): Result<List<NetworkWeatherForecast>> =
        withContext(ioDispatcher){
            return@withContext try{
                val result=retrofitClient.getWeatherForecast(cityId,com.pdinc.weatherapp.BuildConfig.API_KEY)
                if (result.isSuccessful){
                    val networkWeatherForecast = result.body()?.weathers
                    Result.Success(networkWeatherForecast)
                }else{
                    Result.Success(null)
                }
            }catch (exception:Exception){
                Result.Error(exception)
            }
        }

    override suspend fun getSearchWeather(query: String): Result<NetworkWeather> =
        withContext(ioDispatcher){
            return@withContext try {
                val result=retrofitClient.getSpecificWeather(query, com.pdinc.weatherapp.BuildConfig.API_KEY)
                if(result.isSuccessful){
                    val networkWeather=result.body()
                    Result.Success(networkWeather)
                }else{
                    Result.Success(null)
                }
            }catch (exception:Exception){
                Result.Error(exception)
            }
        }

}