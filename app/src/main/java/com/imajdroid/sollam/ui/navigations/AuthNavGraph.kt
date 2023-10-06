package com.imajdroid.sollam.ui.navigations

import android.content.Context
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.google.android.gms.auth.api.identity.Identity
import com.imajdroid.sollam.presentation.sign_in.GoogleAuthUiClient
import com.imajdroid.sollam.presentation.sign_in.SignInViewModel
import com.imajdroid.sollam.ui.screens.SignInScreen
import kotlinx.coroutines.launch

fun NavGraphBuilder.AuthNavGraph(navController: NavController, context: Context, lifecycleScope: LifecycleCoroutineScope) {


    val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = context,
            oneTapClient = Identity.getSignInClient(context)
        )
    }

    navigation(startDestination = "sign_in", route="auth"){
        composable("sign_in") {
            val viewModel = viewModel<SignInViewModel>()
            val state by viewModel.state.collectAsStateWithLifecycle()

            LaunchedEffect(key1 = Unit){
                if(googleAuthUiClient.getSignedInUser() != null){
                    navController.navigate("main")
                }
            }

            val launcher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.StartIntentSenderForResult(),
                onResult = { result ->
                    if (result.resultCode == ComponentActivity.RESULT_OK) {
                        lifecycleScope.launch {
                            val signInResult = googleAuthUiClient.signInWithIntent(
                                intent = result.data ?: return@launch
                            )
                            viewModel.onSignInResult(signInResult)
                        }
                    }
                }
            )

            LaunchedEffect(key1 = state.isSignInSuccessful) {
                if (state.isSignInSuccessful) {
                    Toast.makeText(
                        context,
                        "Successful",
                        Toast.LENGTH_LONG
                    ).show()

                    navController.navigate("main")
                    viewModel.resetState()
                }
            }
            SignInScreen(state = state, onSignInClick = {
                lifecycleScope.launch {
                    val signInIntentSender = googleAuthUiClient.signIn()

                    launcher.launch(
                        IntentSenderRequest.Builder(
                            signInIntentSender ?: return@launch
                        ).build()
                    )
                }
            }
            )
        }
    }
}