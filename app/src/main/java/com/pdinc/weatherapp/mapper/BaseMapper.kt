package com.pdinc.weatherapp.mapper

interface BaseMapper<E,D> {
        fun transfromToDomain(type: E): D
        fun transformToDto(type: D): E //Data Transfer Object

}