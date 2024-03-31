package com.imajdroid.sollam.repository.city

import com.imajdroid.sollam.pojo.City

interface CityRepo {

    fun getCities(): List<City>

}