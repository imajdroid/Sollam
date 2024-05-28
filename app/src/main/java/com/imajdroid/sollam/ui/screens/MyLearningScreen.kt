package com.imajdroid.sollam.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.imajdroid.sollam.R
import com.imajdroid.sollam.Vals
import com.imajdroid.sollam.repository.course.CourseViewModel
import com.imajdroid.sollam.ui.items.FullScreenCircularIndicator
import com.imajdroid.sollam.ui.items.WideCardView

@Composable
fun MyLearningScreen(onNavToStore:() -> Unit
, onNavToCourse: (String) -> Unit
){

    val vm = viewModel<CourseViewModel>()
    LaunchedEffect(key1 = Unit) {
        vm.getMyCourses()
    }
    val loadingState = vm.state
    val courses = vm.courses


    Column(
        modifier = Modifier
            .fillMaxSize(),
        Arrangement.Center,
        Alignment.CenterHorizontally) {


        if(loadingState){
            FullScreenCircularIndicator()
        }


        if (courses.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                items(courses) { course ->

                    val image = course.coverUrl

                    WideCardView(imageUrl = image, title = course.title, desc = course.desc,
                        onClick = { onNavToCourse(course.courseId) }
                    )
                }
            }
        } else {

            Column(modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
                ) {
                Image(
                    modifier = Modifier
                        .fillMaxWidth(),
                    painter = painterResource(id = R.drawable.no_data),
                    contentDescription = null
                )


                Text(
                    modifier = Modifier
                        .padding(bottom = 16.dp, top = 8.dp),
                    text = "انت لم تقم بالإشتراك في أي دورة حتى الآن",
                    fontWeight = FontWeight.Bold,
                    fontFamily = Vals.tajwal
                )
                Button(
                    onClick = {

                        onNavToStore.invoke()

                    }) {
                    Text(
                        text = "اشتراك",
                        fontFamily = Vals.tajwal,
                    )
                }
            }
        }
    }

}