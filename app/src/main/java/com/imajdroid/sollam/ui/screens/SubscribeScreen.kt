package com.imajdroid.sollam.ui.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AutoMode
import androidx.compose.material.icons.outlined.Password
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.imajdroid.sollam.R
import com.imajdroid.sollam.Vals
import com.imajdroid.sollam.viewmodels.SubscribeViewModel

@Composable
fun SubscribeScreen(courseId: String, onNavToCourse: () -> Unit){


    val vm = viewModel<SubscribeViewModel>()


    if(vm.result["success"] == true)
        onNavToCourse()




    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {




        Image(painterResource(
            id = R.drawable.baseline_access_time_24),
            contentDescription = null,
            )

        OutlinedTextField(

            label = {
              Text(text = "أدخل رقم البطاقة",
                  fontFamily = Vals.tajwal,
              )
            },
            textStyle = TextStyle(textDirection = TextDirection.Ltr),
            singleLine = true,
            leadingIcon = {
                Icon(imageVector = Icons.Outlined.Password, contentDescription = "Card number")
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),

            value = vm.code,
            onValueChange = {
                if(it.length <= 14){
                    vm.setCode(it)
                }
            })
        
        
        Button(
            modifier = Modifier
                .padding(16.dp),

            onClick = {
                vm.subscribe(courseId)

            
        }) {
            Text(
                modifier = Modifier
                    .padding(8.dp, 0.dp),
                text = "اشتراك",
                fontFamily = Vals.tajwal,
            )
            Icon(imageVector = Icons.Outlined.AutoMode, contentDescription = null)
        }

    }


}