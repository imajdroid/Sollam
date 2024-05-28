package com.imajdroid.sollam.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FormatListNumberedRtl
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.outlined.AccessTime
import androidx.compose.material.icons.outlined.FormatListNumberedRtl
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Money
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.google.firebase.auth.FirebaseAuth
import com.imajdroid.sollam.Vals
import com.imajdroid.sollam.pojo.Course
import com.imajdroid.sollam.repository.course.CourseViewModel
import com.imajdroid.sollam.ui.components.DescriptionText
import com.imajdroid.sollam.ui.components.SubtitleText
import com.imajdroid.sollam.ui.components.TitleText
import com.imajdroid.sollam.ui.items.FullScreenCircularIndicator


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CourseDetailsScreen(courseId: String, onNavToCourse:(String) -> Unit, onNavToSubscribe:(String)-> Unit) {

    val viewModel = viewModel<CourseViewModel>()


    LaunchedEffect(key1 = Unit){
        viewModel.getCourseAndCheckIfOwned(courseId)
    }

    val course = viewModel.singleCourse
    val isCourseOwned = viewModel.isCourseOwned
    val loadingState = viewModel.state


    while(loadingState){
        FullScreenCircularIndicator()
        return
    }


    Scaffold(modifier = Modifier.fillMaxSize(),



        content = {LazyColumn(Modifier.padding(it)){

            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {

                    AsyncImage(

                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp),
                        model = course.coverUrl,
                        contentScale = ContentScale.Crop,
                        contentDescription = ""

                    )

                    TitleText(text = course.title)

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(0.dp, 16.dp, 0.dp, 16.dp),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                modifier = Modifier.padding(horizontal = 2.dp),
                                imageVector = Icons.Outlined.AccessTime,
                                contentDescription = "Time icon"
                            )
                            Text(
                                text = "${course.duration["stringFormat"]}",
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                modifier = Modifier.padding(horizontal = 2.dp),
                                imageVector = Icons.Outlined.Money,
                                contentDescription = "Time icon"
                            )
                            Text(
                                text = "${course.price.toInt()} دينار ",
                                fontWeight = FontWeight.Bold
                            )
                        }
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
                        Column {
                            Text(
                                modifier = Modifier.padding(16.dp, 0.dp),
                                text = "المدرب",
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                modifier = Modifier.padding(16.dp, 0.dp),
                                text = "${course.instructor["instructorName"]}",

                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    val tabItems = listOf(
                        TabItem(
                            title="نبذة",
                            unselectedIcon = Icons.Outlined.Info,
                            selectedIcon = Icons.Filled.Info
                        ),
                        TabItem(
                            title="أجزاء الدورة",
                            unselectedIcon = Icons.Outlined.FormatListNumberedRtl,
                            selectedIcon = Icons.Filled.FormatListNumberedRtl
                        ),
    //                    TabItem(
    //                        title="التقييم",
    //                        unselectedIcon = Icons.Outlined.StarOutline,
    //                        selectedIcon = Icons.Filled.Star
    //                    )
                    )

                    var selectedTabIndex by remember{
                        mutableIntStateOf(0)
                    }

                    var pagerState = rememberPagerState {
                        tabItems.size
                    }

                    LaunchedEffect(key1 = selectedTabIndex){
                        pagerState.animateScrollToPage(selectedTabIndex)
                    }
                    LaunchedEffect(key1 = pagerState.currentPage, pagerState.isScrollInProgress){
                        if(!pagerState.isScrollInProgress)
                            selectedTabIndex = pagerState.currentPage
                    }

                    TabRow(selectedTabIndex = selectedTabIndex) {
                        tabItems.forEachIndexed{index, tabItem ->
                            Tab(selected = index == selectedTabIndex,
                                onClick = {
                                    selectedTabIndex = index
                                },
                                text = {
                                    Text(text = tabItem.title)
                                },
                                icon = {
                                    Icon(
                                        imageVector =
                                        if(index == selectedTabIndex)
                                            tabItem.selectedIcon
                                        else
                                            tabItem.unselectedIcon,
                                        contentDescription = "Icon"
                                    )
                                }
                            )

                        }
                    }

                    HorizontalPager(
                        verticalAlignment = Alignment.Top,
                        modifier = Modifier
                            .heightIn(min = 700.dp)
                            .fillMaxWidth(),

                        state = pagerState) {index->



                        when (pagerState.currentPage) {

                            0 -> {
                                Brief(course)
                            }

                            1 -> Content(course)
                            2 -> Reviews()
                        }

                    }
                }
            }
        }
    },

            bottomBar = {
                
                if(isCourseOwned){
                    BottomButton(text = "فتح الدورة", courseId=course.courseId){
                        onNavToCourse(course.courseId)
                    }
                }else{
                    BottomButton(text = "إشتراك", courseId = course.courseId) {
                        onNavToSubscribe(course.courseId)
                    }
                }
             
            }
        )
    }


@Composable
private fun BottomButton(text: String, courseId: String, onClick:(String)-> Unit){
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(60.dp),

        onClick = {
            onClick(courseId)
        })
    {
        Text(text = text,
            fontFamily = Vals.tajwal
        )
    }
}

    @Composable
    fun Brief(course : Course){

        Column(
            modifier =
            Modifier
                .fillMaxHeight()
                .fillMaxWidth()
        ) {
            HorizontalDivider(modifier = Modifier.fillMaxWidth())
            for (section in course.details) {
                SubtitleText(text = (section["subtitle"] as String))
                DescriptionText(text = (section["paragraph"] as String))
            }

        }
    }
    @Composable
    fun Content(course: Course){
        Column(
            modifier =
            Modifier
                .fillMaxWidth()
                ,
    //        verticalArrangement = Arrangement.Center
        ) {
            for (section in course.content) {
                Row(

                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)

                ) {
                    Text(text = "${(section["title"])}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        fontFamily = Vals.tajwal
                        )
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 32.dp)
                ) {
                    val lessonsList= (section["lessons"] as List<*>)

                    for(lesson in lessonsList){
                        Text(text = "${(lesson as HashMap<String, Any>)["lessonTitle"]}",
                            fontFamily = Vals.tajwal
                        )

                    }
                }
                HorizontalDivider(modifier = Modifier.fillMaxWidth())

            }

        }

}

@Composable
fun Reviews(){

}


data class TabItem(
    val title: String,
    val unselectedIcon: ImageVector,
    val selectedIcon: ImageVector
)



//        items(course.details){ section->
//            SubtitleText(text = (section["subtitle"] as String))
//            DescriptionText(text = (section["paragraph"] as String))
//        }
//    }


//}




