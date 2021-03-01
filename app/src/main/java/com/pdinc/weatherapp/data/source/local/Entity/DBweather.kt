package com.pdinc.weatherapp.data.source.local.Entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pdinc.weatherapp.data.models.NetworkWeatherCondition
import com.pdinc.weatherapp.data.models.NetworkWeatherDescription
import com.pdinc.weatherapp.data.models.Wind

@Entity(tableName="weather_table")
data class DBweather(
    @ColumnInfo(name = "uniqueId")
    @PrimaryKey(autoGenerate = true)
    var uId: Int=0,

@ColumnInfo(name = "City_Id")
val cityId:Int,

    @ColumnInfo(name = "city_name")
    val cityName:String,

    @Embedded
    val wind:Wind,

    @ColumnInfo(name = "weather_details")
    val networkWeatherDescription: List<NetworkWeatherDescription>,

    @Embedded
    val networkWeatherCondition: NetworkWeatherCondition
)
