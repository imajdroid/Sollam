package com.imajdroid.eschool.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.imajdroid.eschool.Vals
import com.imajdroid.eschool.pojo.Lesson
import com.imajdroid.eschool.ui.items.LessonItem
import com.imajdroid.eschool.viewmodels.OwnedCourseViewModel


@Composable
fun OwnedLessonsScreen(courseId: String, sectionId: String, onLessonClick: (lessonId: String) -> Unit){


    val vm = viewModel<OwnedCourseViewModel>()

    LaunchedEffect(key1 = Unit ){
        vm.getCourse(courseId)
    }

    val course = vm.course
    val loadingState = vm.loadingState
    val lessons = ArrayList<Lesson>()
    course.sections.forEach{section->
        if(section.sectionId == sectionId)
            lessons.addAll(section.lessons)
    }


    if(loadingState == Vals.STATE_LOADING){

        ShimmerEffectScreen()

        //FullScreenCircularIndicator()
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyColumn(){
            items(lessons){lesson->
                LessonItem(lesson){
                    onLessonClick(lesson.contentLocation)
                }
            }
        }
    }

}
