package com.imajdroid.sollam.repository.course

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imajdroid.sollam.Vals
import com.imajdroid.sollam.pojo.Course
import kotlinx.coroutines.launch

public class CourseViewModel(): ViewModel() {

    private val repo = CourseImp()

    private var _courses = mutableStateOf(ArrayList<Course>())


    val courses : List<Course>
        get() = _courses.value

    private var _state=  mutableIntStateOf(Vals.STATE_NOT_LOADING)

    val state : Int
        get() = _state.intValue


    fun getCourses(){

      viewModelScope.launch {
          _state.intValue = Vals.STATE_LOADING
          _courses.value = repo.getCourses()
          _state.intValue = Vals.STATE_NOT_LOADING
      }
  }

    fun getCoursesByCategory(categoryId: String){
        viewModelScope.launch {
            _state.intValue = Vals.STATE_LOADING
            _courses.value = repo.getCoursesByCategory(categoryId)
            _state.intValue = Vals.STATE_NOT_LOADING
        }
    }



    //Single course
    private var _singleCourse = mutableStateOf(Course())
    val singleCourse : Course
        get() = _singleCourse.value

    fun getCourseById(courseId: String){
        viewModelScope.launch {
            _state.intValue = Vals.STATE_LOADING
            _singleCourse.value = repo.getCourseById(courseId)
            _state.intValue = Vals.STATE_NOT_LOADING
        }

    }



}