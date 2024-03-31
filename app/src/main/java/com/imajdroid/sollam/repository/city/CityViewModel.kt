package com.imajdroid.sollam.repository.city

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

import androidx.lifecycle.viewModelScope
import com.imajdroid.sollam.Vals
import com.imajdroid.sollam.pojo.City
import kotlinx.coroutines.launch

class CityViewModel(): ViewModel() {

    private val repo = CityImp()

    private var _cities = mutableStateOf(ArrayList<City>())

    val cities : List<City>
        get() = _cities.value


    private var _loadingState = mutableIntStateOf(Vals.STATE_NOT_LOADING)
    val loadingState : Int
        get() = _loadingState.value


    fun getCities(){
        viewModelScope.launch {
            _loadingState.intValue = Vals.STATE_LOADING
            _cities.value = repo.getCities()
            _loadingState.intValue = Vals.STATE_NOT_LOADING
        }

    }

}