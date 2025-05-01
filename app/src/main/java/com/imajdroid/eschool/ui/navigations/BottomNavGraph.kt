package com.imajdroid.eschool.ui.navigations

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.firebase.auth.FirebaseAuth
import com.imajdroid.eschool.repository.sign_in.UserData
import com.imajdroid.eschool.ui.screens.CategoriesScreen
import com.imajdroid.eschool.ui.screens.CourseDetailsScreen
import com.imajdroid.eschool.ui.screens.HomeScreen
import com.imajdroid.eschool.ui.screens.MyLearningScreen
import com.imajdroid.eschool.ui.screens.OwnedCourseScreen
import com.imajdroid.eschool.ui.screens.OwnedLessonsScreen
import com.imajdroid.eschool.ui.screens.PlayLesson
import com.imajdroid.eschool.ui.screens.ProfileScreen
import com.imajdroid.eschool.ui.screens.StoreCoursesListScreen
import com.imajdroid.eschool.ui.screens.SubscribeScreen
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Composable
fun BottomNavGraph(navController: NavHostController


) {

    NavHost(navController = navController, route = "bottom",
        startDestination = "my_learning"){


        //Home
        composable(route= "home"){
            HomeScreen()
        }


        //My Learning
        composable(route= "my_learning"){



            MyLearningScreen(
                onNavToSubscribe = {
                navController.navigate("subscribe")
            },
                onNavToCourse = { courseId: String->
                    navController.navigate("owned_course/$courseId")
                }


            )
        }


        //Categories
        composable(route= "store"){

            CategoriesScreen(

                onBackPressed = {
                },
                onNavigateToCourseDetails = {
                        categoryId: String ->
                    navController.navigate("store_courses/$categoryId")
                }

            )
//            StoreScreen() { courseId: String ->
//                navController.navigate("course_details/$courseId")
//            }
        }
        //Courses List
        composable(
            route = "store_courses/{category_id}",
            arguments = listOf(
                navArgument("category_id"){
                    type = NavType.StringType
                }
            )
        ){
            val categoryId = it.arguments?.getString("category_id") ?:""
            StoreCoursesListScreen(categoryId = categoryId) { courseId->
               navController.navigate("course_details/$courseId")
            }

        }

        //Subscribe
        composable(
            route= "subscribe",

        ){
            SubscribeScreen()
        }

        //Owned_course
        composable(
            route = "owned_course/{course_id}",
            arguments = listOf(
                navArgument("course_id"){
                    type = NavType.StringType
                }
            )
        ){
            val courseId = it.arguments?.getString("course_id") ?: ""
            OwnedCourseScreen(courseId = courseId){ sectionId->
                navController.navigate("owned_lessons/$courseId/$sectionId")
            }
        }

        //Owned_Lessons
        composable(
            route = "owned_lessons/{course_id}/{section_id}",
            arguments = listOf(
                navArgument("course_id"){
                    type = NavType.StringType
                },
                navArgument("section_id"){
                    type = NavType.StringType
                }
            )
        ){
            val courseId = it.arguments?.getString("course_id") ?: ""
            val sectionId = it.arguments?.getString("section_id") ?: ""

            OwnedLessonsScreen(courseId = courseId, sectionId = sectionId){
                //Play video
                navController.navigate("play_lesson")
            }
        }

        //play_lesson
        composable(
            route = "play_lesson",
//            arguments = listOf(
//                navArgument("category_id"){
//                    type = NavType.StringType
//                }
//            )
        ){
//            val categoryId = it.arguments?.getString("category_id") ?:""
            PlayLesson()

        }

        //Course details
        composable(
            route = "course_details/{course_id}",
            arguments = listOf(
                navArgument("course_id"){
                    type = NavType.StringType
                }
            )
        ){
            val courseId = it.arguments?.getString("course_id") ?:""
            CourseDetailsScreen(courseId = courseId,
                onNavToCourse = {
                                navController.navigate("owned_course/${courseId}")
                },
                onNavToSubscribe = {
                    navController.navigate("subscribe/${courseId}")
                }
                )
        }

        //Profile
        composable(route= "profile"){
            val userData = FirebaseAuth.getInstance().currentUser!!
            ProfileScreen(UserData(userData.uid, userData.displayName, userData.photoUrl.toString()),

                onSignOut = {
                    GlobalScope.launch {
                        FirebaseAuth.getInstance().signOut()

                        //navController.popBackStack()
                    }
                })
        }

    }

}