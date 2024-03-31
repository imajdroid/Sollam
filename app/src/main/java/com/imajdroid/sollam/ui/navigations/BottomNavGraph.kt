package com.imajdroid.sollam.ui.navigations

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.firebase.auth.FirebaseAuth
import com.imajdroid.sollam.repository.sign_in.UserData
import com.imajdroid.sollam.ui.screens.CategoriesScreen
import com.imajdroid.sollam.ui.screens.CourseDetailsScreen
import com.imajdroid.sollam.ui.screens.HomeScreen
import com.imajdroid.sollam.ui.screens.MyLearningScreen
import com.imajdroid.sollam.ui.screens.ProfileScreen
import com.imajdroid.sollam.ui.screens.StoreCoursesListScreen
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Composable
fun BottomNavGraph(navController: NavHostController


) {

    NavHost(navController = navController, route = "bottom",
        startDestination = "home"){


        //Home
        composable(route= "home"){
            HomeScreen()
        }


        //My Learning
        composable(route= "my_learning"){
            MyLearningScreen()
        }


        //Categories
        composable(route= "store"){

            CategoriesScreen(){
                 categoryId: String ->
                    navController.navigate("store_courses/$categoryId")
            }
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
            CourseDetailsScreen(courseId = courseId)

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