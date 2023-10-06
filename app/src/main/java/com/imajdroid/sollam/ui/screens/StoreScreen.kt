package com.imajdroid.sollam.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.imajdroid.sollam.R
import com.imajdroid.sollam.Vals
import com.imajdroid.sollam.presentation.course.CourseViewModel
import com.imajdroid.sollam.ui.items.AnimatedBorderCard
import com.imajdroid.sollam.ui.items.FullScreenCircularIndicator
import com.imajdroid.sollam.ui.items.WideCardView


@Composable
fun StoreScreen(onNavigateToCourseDetails: (String) -> Unit){


    val viewModel = viewModel<CourseViewModel>()

    LaunchedEffect(key1 = Unit) {
        viewModel.getCourses()
    }

    val courses = viewModel.courses
    val state = viewModel.state


    while(state == Vals.STATE_LOADING){
        FullScreenCircularIndicator()
        return
    }
    LazyColumn(
        contentPadding = PaddingValues(all = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ){
        items(courses){ course->
            AnimatedBorderCard(
                onCardClick = {
                }
            ) {

                WideCardView(
                    image = R.drawable.math,
                    title = course.title,
                    desc = "${course.price}",
                    onClick ={
                        onNavigateToCourseDetails(course.courseId)
                    }

                )
            }
        }
    }
}