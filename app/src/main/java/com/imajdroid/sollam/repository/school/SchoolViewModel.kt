package com.imajdroid.sollam.repository.school

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imajdroid.sollam.Vals
import com.imajdroid.sollam.pojo.School
import kotlinx.coroutines.launch

class SchoolViewModel() : ViewModel() {


    private val repo = SchoolImp()

    private var _schools = mutableStateOf(ArrayList<School>())

    val schools : List<School>
        get() = _schools.value






    fun getCategorizedSchoolsOfCity(cityId: String, isPrivate: Boolean){
        viewModelScope.launch {
            _schools.value = repo.getCategorizedSchoolsOfCity(cityId, isPrivate)
        }
    }



}