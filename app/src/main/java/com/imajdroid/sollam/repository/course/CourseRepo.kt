package com.imajdroid.sollam.repository.course

import com.imajdroid.sollam.pojo.Course

interface CourseRepo {


    fun getCourses(): List<Course>
}