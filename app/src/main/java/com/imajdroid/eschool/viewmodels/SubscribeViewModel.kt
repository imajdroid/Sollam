package com.imajdroid.eschool.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imajdroid.eschool.repository.subscribe.SubscribeImp
import kotlinx.coroutines.launch

class SubscribeViewModel: ViewModel() {


    private val repo = SubscribeImp()

    private val _code = mutableStateOf("")
    private var _result = mutableStateOf(HashMap<String, Any>())
    private val _success = mutableStateOf(false)
    private val _done = mutableStateOf(false)
    fun setCode(code: String){
        _code.value = code
    }
    val code : String
        get() = _code.value

    val result: HashMap<String, Any>
        get() = _result.value
    val success: Boolean
        get() = _success.value

    fun subscribe(courseId: String){
        viewModelScope.launch {
            _result.value = repo.subscribe(code, courseId)

        }
    }





}