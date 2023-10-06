package com.imajdroid.sollam.presentation.course

import com.imajdroid.sollam.pojo.Course

interface CourseRepo {


    fun getCourses(): List<Course>
}