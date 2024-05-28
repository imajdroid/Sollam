package com.imajdroid.sollam.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Password
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext


import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.imajdroid.sollam.Vals
import com.imajdroid.sollam.viewmodels.SignInViewModel


@Composable
fun SignInScreen(
    signInViewModel1: SignInViewModel? = null,
    onNavToHomeScreen: () -> Unit,
    onNavToSignUpScreen: () -> Unit,

    ){

    val signInViewModel = viewModel<SignInViewModel>()

    val signInUIState = signInViewModel.signInUIState

    val isError = signInUIState.signInError != null

    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "تسجيل الدخول",
            fontFamily = Vals.tajwal,
            style = MaterialTheme.typography.headlineMedium,
//            fontWeight = FontWeight.Black.weight,
            color = MaterialTheme.colorScheme.primary)
        
        if(isError){
            Text(text = signInUIState.signInError ?: "حدث خطأ غير معروف",
                fontFamily = Vals.tajwal,
                color = Color.Red)
        }

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            value = signInUIState.email ?: "",
            onValueChange = {ite->signInViewModel.onEmailChange(ite)},
            leadingIcon = {
                Icon(imageVector = Icons.Outlined.Email, contentDescription = "Email")
            },
            label = {
                Text(text = "البريد الإلكتروني",
                    fontFamily = Vals.tajwal,
                )
            },
            isError = isError
        )



        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            value = signInUIState.password ?: "",
            onValueChange = {signInViewModel.onPasswordChange(it)},
            leadingIcon = {
                Icon(imageVector = Icons.Outlined.Password, contentDescription = "password")
            },
            label = {
                Text(text = "كلمة المرور",
                    fontFamily = Vals.tajwal,
                )
            },

            visualTransformation = PasswordVisualTransformation(),
            isError = isError
            )

        Button(onClick = {signInViewModel.signIn(context)}) {
            Text(text = "تسجيل الدخول",
                fontFamily = Vals.tajwal,
            )
        }

        Spacer(modifier = Modifier.size(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "ليس لديك حساب؟",
                fontFamily = Vals.tajwal
            )
            Spacer(modifier = Modifier.size(8.dp))
            TextButton(onClick = { onNavToSignUpScreen.invoke() }) {
                Text(text = "إنشاء حساب",
                    fontFamily = Vals.tajwal
                )
            }
        }
    }

    if(signInUIState.isLoading){
        CircularProgressIndicator()
    }
    
//    LaunchedEffect(key1 = signInViewModel.hasUser){
//        if(signInViewModel?.hasUser == true)
//            onNavToHomeScreen.invoke()
//    }

}

@Preview(showSystemUi = true)
@Composable
fun PrevSignInScreen(){
    SignInScreen(onNavToHomeScreen = { /*TODO*/ }) {
        
    }

}




