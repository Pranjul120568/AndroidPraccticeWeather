package com.pdinc.weatherapp.utils

import java.lang.Exception

//this is class is just used to make the retrofit calls not interact
// with the system directly but make there last step indirect or bypassed
sealed class Result <out R>{
    data class Success<T>(val data: T?):Result<T>()
    data class Error(val exception: Exception):Result<Nothing>()
    object Loading: Result<Nothing>()

    override fun toString(): String {
        return when(this){
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception = $exception]"
            is Loading -> "Loading"
        }
    }
}