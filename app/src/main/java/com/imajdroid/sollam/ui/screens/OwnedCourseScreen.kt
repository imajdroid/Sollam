package com.imajdroid.sollam.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.imajdroid.sollam.Vals
import com.imajdroid.sollam.ui.items.FullScreenCircularIndicator
import com.imajdroid.sollam.ui.items.SectionItem
import com.imajdroid.sollam.ui.items.ShimmerListItem
import com.imajdroid.sollam.viewmodels.OwnedCourseViewModel


@Composable
fun OwnedCourseScreen(courseId: String, onSectionClick: (sectionId: String) -> Unit){


    val vm = viewModel<OwnedCourseViewModel>()

    LaunchedEffect(key1 = Unit ){
        vm.getCourse(courseId)
    }

    val course = vm.course
    val sections = course.sections

    val loadingState = vm.loadingState

    if(loadingState == Vals.STATE_LOADING){

        ShimmerEffectScreen()
        
        //FullScreenCircularIndicator()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyColumn(){

            items(sections){section->
                SectionItem(section){

                    onSectionClick(section.sectionId)

                }
            }

        }
    }

}
