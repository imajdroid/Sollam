package com.imajdroid.sollam.ui.navigations

import android.content.Context

import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.google.android.gms.auth.api.identity.Identity
import com.imajdroid.sollam.repository.sign_in.GoogleAuthUiClient
import com.imajdroid.sollam.ui.screens.AddStudentDataScreen
import com.imajdroid.sollam.ui.screens.SignInScreen
import com.imajdroid.sollam.ui.screens.SignUpScreen

fun NavGraphBuilder.AuthNavGraph(navController: NavController) {


    navigation(startDestination = "sign_in", route="auth"){
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
            AddStudentDataScreen()
        }
    }



}