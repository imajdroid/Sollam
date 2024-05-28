package com.imajdroid.sollam.repository.student

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imajdroid.sollam.Vals
import com.imajdroid.sollam.pojo.Student
import kotlinx.coroutines.launch

class StudentViewModel: ViewModel() {


    private val repo = StudentImp()

    private val _student = mutableStateOf(Student())
    private val _isLoading = mutableStateOf(true)
    val student : Student
        get() = _student.value


    val isLoading : Boolean
        get() = _isLoading.value
    fun getStudentData(){

        viewModelScope.launch {
            _isLoading.value = true
            _student.value = repo.getStudentData()
            _isLoading.value = false
        }
    }

}