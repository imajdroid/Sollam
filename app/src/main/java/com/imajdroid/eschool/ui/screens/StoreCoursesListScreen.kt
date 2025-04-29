package com.imajdroid.eschool.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.imajdroid.eschool.repository.course.CourseViewModel
import com.imajdroid.eschool.ui.items.AnimatedBorderCard
import com.imajdroid.eschool.ui.items.FullScreenCircularIndicator
import com.imajdroid.eschool.ui.items.WideCardView


@Composable
fun StoreCoursesListScreen(categoryId: String, onNavigateToCourseDetails: (String) -> Unit){


    val viewModel = viewModel<CourseViewModel>()

    LaunchedEffect(key1 = Unit) {
        viewModel.getCoursesByCategory(categoryId)
    }

    val courses = viewModel.courses
    val state = viewModel.state


    while(state){
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
                    imageUrl = course.coverUrl,
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