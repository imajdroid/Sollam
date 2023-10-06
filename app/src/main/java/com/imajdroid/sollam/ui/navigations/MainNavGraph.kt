package com.imajdroid.sollam.ui.navigations

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.imajdroid.sollam.ui.screens.CourseDetailsScreen
import com.imajdroid.sollam.ui.screens.MainScreen


fun NavGraphBuilder.MainNavGraph(navController: NavController) {
    navigation(startDestination = "main_screen", route="main"){
        composable("main_screen") {
            MainScreen()
        }

    }
}