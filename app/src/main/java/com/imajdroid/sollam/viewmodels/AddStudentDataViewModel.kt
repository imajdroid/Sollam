package com.imajdroid.sollam.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import com.imajdroid.sollam.Vals

class AddStudentDataViewModel(): ViewModel() {






    var studentDataState by mutableStateOf(StudentDataState())
        private set


    fun setLoadingState(state: Boolean){
        studentDataState = studentDataState.copy(isLoading = state)
    }

    fun onFirstNameChange(firstName: String){
        studentDataState = studentDataState.copy(firstName = firstName)
    }
    fun onSecondNameChange(secondName: String){
        studentDataState = studentDataState.copy(secondName = secondName)
    }
    fun onThirdNameChange(thirdName: String){
        studentDataState = studentDataState.copy(thirdName = thirdName)
    }
    fun onSurnameChange(surname: String){
        studentDataState = studentDataState.copy(surname = surname)
    }

    fun onCityIdChange(cityId: String, cityName: String){
        studentDataState = studentDataState.copy(cityId = cityId, cityName = cityName)

    }

    fun onPrivateStateChange(isPrivate: Boolean){
        studentDataState = studentDataState.copy(isPrivate = isPrivate)
    }
    fun onSchoolIdChange(schoolId: String, schoolName: String){
        studentDataState = studentDataState.copy(schoolId = schoolId, schoolName = schoolName)
    }

    fun onGradeIdChange(gradeId: String, gradeName: String){
        studentDataState = studentDataState.copy(gradeId = gradeId, gradeName = gradeName)
    }

    fun onPhoneNumberChange(phoneNumber: String){
        studentDataState = studentDataState.copy(phoneNumber = phoneNumber)
    }

    fun onProfilePictureUrlChange(profilePictureUrl: String){
        studentDataState = studentDataState.copy(profilePictureUrl = profilePictureUrl)
    }

    fun onEmailChange(email: String){
        studentDataState = studentDataState.copy(email = email)
    }

    fun setNamesError(error: Boolean){
        studentDataState = studentDataState.copy(namesError = error)
    }
    fun setCityError(error: Boolean){
        studentDataState = studentDataState.copy(cityError = error)
    }
    fun setSchoolError(error: Boolean){
        studentDataState = studentDataState.copy(schoolError = error)
    }
    fun setGradeError(error: Boolean){
        studentDataState = studentDataState.copy(gradeError = error)
    }
    fun setPhoneNumberError(error: Boolean){
        studentDataState = studentDataState.copy(phoneNumberError = error)
    }


    fun validateNames() =
        studentDataState.firstName.isNotBlank() &&
                studentDataState.secondName.isNotBlank() &&
                studentDataState.thirdName.isNotBlank() &&
                studentDataState.surname.isNotBlank()

    fun validateCity() =
        studentDataState.cityId.isNotBlank()

    fun validateSchool() =
        studentDataState.schoolId.isNotBlank()

    fun validateGrade() =
        studentDataState.gradeId.isNotBlank()

    fun validatePhoneNumber() =
        studentDataState.phoneNumber.isNotBlank()


    data class StudentDataState(
        val firstName: String = "",
        val secondName: String = "",
        val thirdName: String = "",
        val surname : String = "",
        val cityId: String = "",
        val cityName: String = "",
        val isPrivate: Boolean = true,
        val schoolId: String = "",
        val schoolName: String = "",
        val gradeId: String = "",
        val gradeName: String = "",
        val phoneNumber: String = "",
        val profilePictureUrl: String = "",

        var errorMessage: String = "",


        var namesError: Boolean = false,
        var cityError: Boolean = false,
        var schoolError: Boolean = false,
        var gradeError: Boolean = false,
        var phoneNumberError: Boolean = false,


        val email: String = "",


        var isLoading: Boolean = false
    ){}
}