package com.imajdroid.sollam.ui.navigations

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController




@Composable
fun MyNavHost( lifecycleScope: LifecycleCoroutineScope, context: Context){


    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "auth") {


        AuthNavGraph(navController)
        //BottomNavGraph(navController)
        MainNavGraph(navController)


        }

    }
