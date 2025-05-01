package com.imajdroid.eschool.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Password
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import com.imajdroid.eschool.ui.theme.BlueMain
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext


import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.imajdroid.eschool.Vals
import com.imajdroid.eschool.viewmodels.SignInViewModel


@Composable
fun SignInScreen(
    signInViewModel1: SignInViewModel? = null,
    onNavToHomeScreen: () -> Unit,
    onNavToSignUpScreen: () -> Unit,

    ){

    val signInViewModel = viewModel<SignInViewModel>()

    val signInUIState = signInViewModel.signInUIState

    val isError = signInUIState.signInError != null

    var passwordVisible by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
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
            isError = isError,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = BlueMain,
                unfocusedBorderColor = BlueMain,
                focusedLeadingIconColor = BlueMain,
                unfocusedLeadingIconColor = BlueMain,

        )

        )



        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            value = signInUIState.password ?: "",
            onValueChange = { signInViewModel.onPasswordChange(it) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Password,
                    contentDescription = "كلمة المرور"
                )
            },
            label = {
                Text(
                    text = "كلمة المرور",
                    fontFamily = Vals.tajwal,
                )
            },
            isError = isError,

            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = BlueMain,
                unfocusedBorderColor = BlueMain,
                focusedLeadingIconColor = BlueMain,
                unfocusedLeadingIconColor = BlueMain,

                ),

            visualTransformation = if (passwordVisible) VisualTransformation.None
            else PasswordVisualTransformation(),

            trailingIcon = {
                val image = if (passwordVisible)
                    Icons.Filled.Visibility
                else Icons.Filled.VisibilityOff

                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(imageVector = image, contentDescription = "Toggle password visibility")
                }
            }


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




