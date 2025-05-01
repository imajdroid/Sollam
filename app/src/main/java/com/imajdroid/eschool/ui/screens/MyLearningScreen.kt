package com.imajdroid.eschool.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.imajdroid.eschool.R
import com.imajdroid.eschool.Vals
import com.imajdroid.eschool.repository.course.CourseViewModel
import com.imajdroid.eschool.ui.items.FullScreenCircularIndicator
import com.imajdroid.eschool.ui.items.WideCardView

@Composable
fun MyLearningScreen(onNavToSubscribe:() -> Unit
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

                        onNavToSubscribe.invoke()

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