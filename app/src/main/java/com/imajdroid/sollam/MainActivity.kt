package com.imajdroid.sollam

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection

import androidx.lifecycle.lifecycleScope
import com.imajdroid.sollam.ui.navigations.MyNavHost


import com.imajdroid.sollam.ui.theme.SollamTheme


/*Concepts.

    1- Lazy
    2- LaunchedEffect
    3- rememberLauncherForActivityResult
*/
class MainActivity : ComponentActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SollamTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl ) {
                        MyNavHost(lifecycleScope = lifecycleScope, context = applicationContext)
                    }

                }
            }
        }
    }
}