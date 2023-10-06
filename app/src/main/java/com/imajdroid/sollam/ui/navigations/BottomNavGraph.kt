package com.imajdroid.sollam.ui.navigations

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.firebase.auth.FirebaseAuth
import com.imajdroid.sollam.pojo.Course
import com.imajdroid.sollam.presentation.sign_in.UserData
import com.imajdroid.sollam.ui.screens.CourseDetailsScreen
import com.imajdroid.sollam.ui.screens.HomeScreen
import com.imajdroid.sollam.ui.screens.MyLearningScreen
import com.imajdroid.sollam.ui.screens.ProfileScreen
import com.imajdroid.sollam.ui.screens.StoreScreen
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Composable
fun BottomNavGraph(navController: NavHostController


) {

    NavHost(navController = navController, route = "bottom",
        startDestination = "home"){

        composable(route= "home"){
            HomeScreen()
        }
        composable(route= "my_learning"){
            MyLearningScreen()
        }
        composable(route= "store"){
            StoreScreen() { courseId: String ->
                navController.navigate("course_details/$courseId")
            }
        }

        composable(
            route = "course_details/{course_id}",
            arguments = listOf(
                navArgument("course_id"){
                    type = NavType.StringType
                }
            )
        ){
            val courseId = it.arguments?.getString("course_id") ?:""
            CourseDetailsScreen(courseId = courseId)

        }

        composable(route= "profile"){
            val userData = FirebaseAuth.getInstance().currentUser!!
            ProfileScreen(UserData(userData.uid, userData.displayName, userData.photoUrl.toString()),

                onSignOut = {
                    GlobalScope.launch {
                        FirebaseAuth.getInstance().signOut()
                        navController.popBackStack()
                    }
                })
        }

    }

}