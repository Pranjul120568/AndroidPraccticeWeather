package com.pdinc.weatherapp.data.source.local.Entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pdinc.weatherapp.data.models.NetworkWeatherCondition
import com.pdinc.weatherapp.data.models.NetworkWeatherDescription
import com.pdinc.weatherapp.data.models.Wind

@Entity(tableName = "weather_forecast")
class DBWeatherForecast(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val date: String,

    @Embedded
    val wind: Wind,

    @ColumnInfo(name = "weather_description")
    //Room can't resolve NetworkWeatherDescription that's why we
    // need to make so we can't save custom type of list in DB directly
    // we need a converter for that so we neeed a type converter so when
    // we save the data it converts the data into RawJson string
    val networkWeatherDescriptions: List<NetworkWeatherDescription>,

    @Embedded
    val networkWeatherCondition: NetworkWeatherCondition
)
