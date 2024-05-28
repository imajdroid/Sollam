package com.imajdroid.sollam.ui.screens

import android.media.browse.MediaBrowser.MediaItem
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.MediaSource
import com.google.firebase.storage.FirebaseStorage
import com.imajdroid.sollam.Vals
import com.imajdroid.sollam.pojo.Lesson
import com.imajdroid.sollam.ui.items.LessonItem
import com.imajdroid.sollam.ui.items.ShimmerListItem
import com.imajdroid.sollam.viewmodels.OwnedCourseViewModel


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
