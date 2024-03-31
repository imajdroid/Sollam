package com.imajdroid.sollam.repository.school

import com.imajdroid.sollam.pojo.School

interface SchoolRepo {

    fun getCategorizedSchoolsOfCity(cityId: String, isPrivate: Boolean): List<School>

}