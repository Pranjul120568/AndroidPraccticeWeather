package com.pdinc.weatherapp.data.source.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.pdinc.weatherapp.data.models.NetworkWeatherDescription
import java.lang.reflect.Type

class ListNetworkWeatherDescriptionConverter {
    val gson= Gson()
    val type: Type =object : TypeToken<List<NetworkWeatherDescription>>() {}.type

    @TypeConverter
    fun fromWeatherDtoList(list: List<NetworkWeatherDescription>):String {
        return gson.toJson(list,type)
    }
    @TypeConverter
    fun toWeatherDtoList(json:String):List<NetworkWeatherDescription> {
        return gson.fromJson(json,type)
    }
}