package com.imajdroid.sollam.pojo

data class School(
    val schoolId: String,
    val schoolName: String,
    val cityId: String,
    val isPrivate: Boolean
) {

    constructor(): this("", "", "", false)

}