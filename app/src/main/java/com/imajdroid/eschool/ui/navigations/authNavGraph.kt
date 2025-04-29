package com.imajdroid.eschool.ui.navigations

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.imajdroid.eschool.ui.screens.AddStudentDataScreen
import com.imajdroid.eschool.ui.screens.SignInScreen
import com.imajdroid.eschool.ui.screens.SignUpScreen

fun NavGraphBuilder.authNavGraph(navController: NavController, startDestination: String) {


    navigation(startDestination = startDestination, route="auth"){


        composable("sign_in") {


            SignInScreen(onNavToHomeScreen = {

                navController.navigate("main"){
                    launchSingleTop = true
                    popUpTo(route = "sign_in"){
                        inclusive = true
                    }
                }
            },
                onNavToSignUpScreen = {
                    navController.navigate("sign_up")
                    {
                        launchSingleTop = true
                        popUpTo(route = "sign_in"){
                            inclusive = true
                        }
                    }
                }

            )

        }



        composable("sign_up") {

            SignUpScreen(
                onNavToHomeScreen = {
                    navController.navigate("main"){
                        popUpTo(route = "sign_in"){
                            inclusive = true
                        }
                    }
                },
                onNavToSignInScreen = {
                    navController.navigate("sign_in")

                },

                onNavToAddStudentDataScreen = {
                    navController.navigate("add_student_data")
                }


            )
        }


        composable("add_student_data") {
            AddStudentDataScreen(
                onNavToMain = {
                    navController.navigate("main")
                }
            )
        }
    }



}