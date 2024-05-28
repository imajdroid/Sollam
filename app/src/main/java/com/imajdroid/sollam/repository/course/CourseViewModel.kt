package com.imajdroid.sollam.repository.course

import android.util.Log
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imajdroid.sollam.Vals
import com.imajdroid.sollam.pojo.Course
import kotlinx.coroutines.launch

class CourseViewModel(): ViewModel() {

    private val repo = CourseImp()

    private var _courses = mutableStateOf(ArrayList<Course>())
    private var _state=  mutableStateOf(true)


    private val _isCourseOwned = mutableStateOf(false)
    val isCourseOwned: Boolean
        get()= _isCourseOwned.value


    val courses : List<Course>
        get() = _courses.value


    val state : Boolean
        get() = _state.value


    fun getCourses(){
      viewModelScope.launch {
          _state.value = true
          _courses.value = repo.getCourses()
          _state.value = false
      }
  }

    fun getMyCourses(){
        viewModelScope.launch {
            _state.value = true
            _courses.value = repo.getMyCourses()
            _state.value = false
        }
    }
    fun getCoursesByCategory(categoryId: String){
        viewModelScope.launch {
            _state.value = true
            _courses.value = repo.getCoursesByCategory(categoryId)
            _state.value = false
        }
    }



    //Single course
    private var _singleCourse = mutableStateOf(Course())
    val singleCourse : Course
        get() = _singleCourse.value

    fun getCourseById(courseId: String){
        viewModelScope.launch {
            _state.value = true
            _singleCourse.value = repo.getCourseById(courseId)
            _state.value = false
        }
    }

    fun getCourseAndCheckIfOwned(courseId: String){
        viewModelScope.launch {
            _state.value = true
            _singleCourse.value = repo.getCourseById(courseId)
            _isCourseOwned.value = repo.isCourseOwned(courseId)

            _state.value = false
        }
    }



}