package com.imajdroid.sollam.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imajdroid.sollam.Vals
import com.imajdroid.sollam.pojo.Course
import com.imajdroid.sollam.pojo.Lesson
import com.imajdroid.sollam.pojo.Section
import com.imajdroid.sollam.repository.course.CourseImp
import kotlinx.coroutines.launch

class OwnedCourseViewModel : ViewModel(){


    private val courseRepo =  CourseImp()

    private var _course = mutableStateOf(Course())
    private var _lessons = mutableStateOf(ArrayList<Lesson>())
    private var _sections = mutableStateOf(ArrayList<Section>())


    val course : Course
        get() = _course.value

    val lessons : List<Lesson>
        get() = _lessons.value

    val sections : List<Section>
        get() = _sections.value

    private var _loadingState=  mutableIntStateOf(Vals.STATE_NOT_LOADING)


    val loadingState : Int
        get() = _loadingState.intValue


    fun getCourse(courseId: String){
        viewModelScope.launch {
            _loadingState.intValue = Vals.STATE_LOADING
            _course.value = courseRepo.getOwnedCourseById(courseId)
            _loadingState.intValue = Vals.STATE_NOT_LOADING
        }
    }

//    fun getLessons(courseId: String, sectionId: String){
//        viewModelScope.launch {
//            _loadingState.intValue = Vals.STATE_LOADING
//            _lessons.value = repo.getLessons(courseId, sectionId)
//            _loadingState.intValue = Vals.STATE_NOT_LOADING
//        }
//    }
//
//    fun getSections(courseId: String){
//        viewModelScope.launch {
//            _loadingState.intValue = Vals.STATE_LOADING
//            _sections.value = repo.getSections(courseId)
//            _loadingState.intValue = Vals.STATE_NOT_LOADING
//        }
//    }
}