package com.imajdroid.sollam.ui.navigations

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.auth.api.identity.Identity
import com.google.firebase.auth.FirebaseAuth
import com.imajdroid.sollam.presentation.sign_in.GoogleAuthUiClient
import com.imajdroid.sollam.presentation.sign_in.SignInViewModel
import com.imajdroid.sollam.presentation.sign_in.UserData
import com.imajdroid.sollam.ui.screens.CourseDetailsScreen
import com.imajdroid.sollam.ui.screens.HomeScreen
import com.imajdroid.sollam.ui.screens.MainScreen
import com.imajdroid.sollam.ui.screens.MyLearningScreen
import com.imajdroid.sollam.ui.screens.ProfileScreen
import com.imajdroid.sollam.ui.screens.SignInScreen
import com.imajdroid.sollam.ui.screens.StoreScreen
import kotlinx.coroutines.launch




@Composable
fun MyNavHost( lifecycleScope: LifecycleCoroutineScope, context: Context){


    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "auth") {


        AuthNavGraph(navController, context, lifecycleScope)
        //BottomNavGraph(navController)
        MainNavGraph(navController)


        }

    }
