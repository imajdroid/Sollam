package com.imajdroid.sollam.ui.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.imajdroid.sollam.R
import com.imajdroid.sollam.Vals
import com.imajdroid.sollam.presentation.course.CourseViewModel
import com.imajdroid.sollam.ui.components.DescriptionText
import com.imajdroid.sollam.ui.components.SubtitleText
import com.imajdroid.sollam.ui.components.TitleText
import com.imajdroid.sollam.ui.items.FullScreenCircularIndicator


@Composable
fun CourseDetailsScreen(courseId: String) {



    val viewModel = viewModel<CourseViewModel>()

    LaunchedEffect(key1 = courseId){
        viewModel.getCourseById(courseId)
    }

    val course = viewModel.singleCourse
    val state = viewModel.state

    while(state == Vals.STATE_LOADING){
        FullScreenCircularIndicator()
        return
    }




            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(state = rememberScrollState())
            ) {

                Image(

                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp),
                    painter = painterResource(R.drawable.math),
                    contentScale = ContentScale.Crop,
                    contentDescription = ""
                )

                TitleText(text = course.title)

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp, 0.dp, 0.dp, 16.dp),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Text(
                        text = "مدة الدورة: ${course.duration["stringFormat"]}",
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "سعر الدورة: ${course.price.toInt()}",
                        fontWeight = FontWeight.Bold
                    )

                }



                Spacer(modifier = Modifier.height(16.dp))

                Row(

                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),

                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AsyncImage(
                        model = "https://gsep.pepperdine.edu/blog/images/how-much-could-a-masters-degree-increase-your-teaching-salary.png",
                        contentDescription = "Profile picture",
                        modifier = Modifier
                            .size(60.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )

                    Text(
                        modifier = Modifier.padding(16.dp, 0.dp),
                        text = "مجدي الهيظب",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))

                var showFullDetails by remember {
                    mutableStateOf(false)
                }

                Column(
                    modifier = if (showFullDetails) {
                        Modifier.wrapContentHeight()
                    } else {
                        Modifier.height(140.dp)
                    }
                        .fillMaxWidth()
                        .clickable {
                            showFullDetails = !showFullDetails
                        }
                        .animateContentSize()
                ) {
                    Divider(modifier = Modifier.fillMaxWidth())
                    for (section in course.details) {
                        SubtitleText(text = (section["subtitle"] as String))
                        DescriptionText(text = (section["paragraph"] as String))
                    }

                }
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable {
                            showFullDetails = !showFullDetails
                        },
                    textAlign = TextAlign.Center,
                    color = androidx.compose.ui.graphics.Color.Blue,
                    textDecoration = TextDecoration.Underline,
                    text = if (showFullDetails) "عرض تفاصيل أقل" else "عرض المزيد من التفاصيل"
                )

                Divider(modifier = Modifier.fillMaxWidth())


                var showCourseSections by remember{
                    mutableStateOf(false)
                }
            Column(
                modifier = if (showCourseSections)
                    Modifier.wrapContentHeight()
                else {
                    Modifier.height(80.dp)
                }
                    .fillMaxWidth()
                    .animateContentSize()
                    .clickable {
                        showCourseSections = !showCourseSections
                    },
                verticalArrangement = Arrangement.Center
                ) {
                SubtitleText(text = "أجزاء الدورة")


                if(showCourseSections) {
                    for (section in course.content) {

                        Row(

                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 8.dp)

                        ) {
                            Text(text = "${(section["title"])}",
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp,

                            )
                        }

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 32.dp)
                        ) {
                            val lessonsList= (section["lessons"] as List<*>)

                            for(lesson in lessonsList){
                                Text(text = "${(lesson as HashMap<String, Any>)["lessonTitle"]}")

                            }
                        }
                        Divider(modifier = Modifier.fillMaxWidth())

                    }
                }
            }
    }
}


//        items(course.details){ section->
//            SubtitleText(text = (section["subtitle"] as String))
//            DescriptionText(text = (section["paragraph"] as String))
//        }
//    }


//}




