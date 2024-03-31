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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.imajdroid.sollam.viewmodels.SignInViewModel


@Composable
fun SignUpScreen(
    signInViewModel1: SignInViewModel? = null,
    onNavToHomeScreen: () -> Unit,
    onNavToSignInScreen: () -> Unit,
    onNavToAddStudentDataScreen: () -> Unit

    ){


    onNavToAddStudentDataScreen.invoke()
    val signInViewModel = viewModel<SignInViewModel>()

    val signInUIState = signInViewModel.signInUIState

    val isError = signInUIState?.signUpError != null

    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "إنشاء حساب",
            style = MaterialTheme.typography.headlineMedium,
//            fontWeight = FontWeight.Black.weight,
            color = MaterialTheme.colorScheme.primary)
        
        if(isError){
            Text(text = signInUIState?.signUpError ?: "حدث خطأ غير معروف",
                color = Color.Red)
        }

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            value = signInUIState?.emailSignUp ?: "",
            onValueChange = {signInViewModel?.onEmailSignUpChange(it)},
            leadingIcon = {
                Icon(imageVector = Icons.Outlined.Email, contentDescription = "Email")
            },
            label = {
                Text(text = "البريد الإلكتروني")
            },
            isError = isError
        )



        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            value = signInUIState?.passwordSignUp ?: "",
            onValueChange = {signInViewModel?.onPasswordSignUpChange(it)},
            leadingIcon = {
                Icon(imageVector = Icons.Outlined.Password, contentDescription = "password")
            },
            label = {
                Text(text = "كلمة المرور")
            },

            visualTransformation = PasswordVisualTransformation(),
            isError = isError
            )


        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            value = signInUIState?.confirmPasswordSignUp ?: "",
            onValueChange = {signInViewModel?.onConfirmPasswordSignUpChange(it)},
            leadingIcon = {
                Icon(imageVector = Icons.Outlined.Password, contentDescription = "password")
            },
            label = {
                Text(text = "أعد كتابة كلمة المرور")
            },

            visualTransformation = PasswordVisualTransformation(),
            isError = isError
        )
        Button(onClick = {signInViewModel?.createUser(context)}) {
            Text(text = "إنشاء الحساب")
        }

        Spacer(modifier = Modifier.size(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "لديك حساب بالفعل؟")
            Spacer(modifier = Modifier.size(8.dp))
            TextButton(onClick = { onNavToSignInScreen.invoke() }) {
                Text(text = "تسجيل الدخول")
            }
        }
    }

    if(signInUIState?.isLoading == true){
        CircularProgressIndicator()
    }
    
    LaunchedEffect(key1 = signInViewModel?.hasUser){
        if(signInViewModel?.hasUser == true)
            onNavToHomeScreen.invoke()
    }

}

@Preview(showSystemUi = true)
@Composable
fun PrevSignUpScreen(){
    SignUpScreen(onNavToHomeScreen = { },
    onNavToSignInScreen = {}
        ) {
        
    }

}




