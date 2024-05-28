package com.imajdroid.sollam.ui.navigations

import android.content.Context
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.imajdroid.sollam.repository.student.StudentViewModel
import com.imajdroid.sollam.ui.items.FullScreenCircularIndicator


@Composable
fun MyNavHost( lifecycleScope: LifecycleCoroutineScope, context: Context){



    val navController = rememberNavController()
    val vm = viewModel<StudentViewModel>()



    val student = vm.student

//    student.studentId = "NOT_STUDENT"

    val user = FirebaseAuth.getInstance().currentUser
    LaunchedEffect(Unit){
        if(user!=null) {
            vm.getStudentData()
        }
    }

    var startDestination = "auth"
    var authStartDestination = "sign_in"


    if (user != null && student.studentId.isNotBlank()) {
        startDestination = "main"
    } else if (user != null && student.studentId.isBlank()) {
        startDestination = "auth"
        authStartDestination = "add_student_data"

    } else {
        startDestination = "auth"
        authStartDestination = "sign_in"
    }


//    if(user != null && user.displayName != null && user.displayName!!.isNotBlank()) {
//        startDestination = "main"
//
//    }else if(user != null && user.displayName != null && user.displayName!!.isBlank()||
//        user != null && user.displayName == null
//        ) {
//
//        startDestination = "auth"
//        authStartDestination = "add_student_data"
//    }else{
//        startDestination = "auth"
//        authStartDestination = "sign_in"
//    }



    if(vm.isLoading){
        FullScreenCircularIndicator()
    }else {
        NavHost(navController = navController, startDestination = startDestination) {
            authNavGraph(navController, authStartDestination)
            mainNavGraph()
        }
    }

}
