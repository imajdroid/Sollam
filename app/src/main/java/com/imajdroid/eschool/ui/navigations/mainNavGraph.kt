package com.imajdroid.eschool.ui.navigations

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.imajdroid.eschool.ui.screens.MainScreen


fun NavGraphBuilder.mainNavGraph() {
    navigation(startDestination = "main_screen", route="main"){
        composable("main_screen") {
            MainScreen()
        }

    }
}