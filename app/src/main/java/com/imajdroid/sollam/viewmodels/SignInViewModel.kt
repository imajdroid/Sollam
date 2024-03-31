package com.imajdroid.sollam.viewmodels

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.imajdroid.sollam.repository.sign_in.AuthRepository
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class SignInViewModel(
    private val repository: AuthRepository = AuthRepository()
): ViewModel() {

    val currentUser = repository.currentUser

    val hasUser: Boolean
        get() = repository.hasUser()

    var signInUIState by mutableStateOf(SignInUIState())
        private set

    fun onEmailChange(email: String){
        signInUIState = signInUIState.copy(email = email)

    }
    fun onPasswordChange(password: String){
        signInUIState = signInUIState.copy(password = password)
    }
    fun onEmailSignUpChange(email: String){
        signInUIState = signInUIState.copy(emailSignUp = email)
    }
    fun onPasswordSignUpChange(password: String){
        signInUIState = signInUIState.copy(passwordSignUp = password)
    }
    fun onConfirmPasswordSignUpChange(password: String){
        signInUIState = signInUIState.copy(confirmPasswordSignUp = password)
    }
    fun onLoadingChange(isLoading: Boolean){
        signInUIState = signInUIState.copy(isLoading = isLoading)
    }
    fun onSuccessChange(isSuccessful: Boolean){
        signInUIState = signInUIState.copy(isSuccessful = isSuccessful)
    }
    fun onErrorChange(error: String){
        signInUIState = signInUIState.copy(signInError = error)
    }
    fun onSignUpErrorChange(signUpError: String){
        signInUIState = signInUIState.copy(signUpError = signUpError)
    }


    private fun validateSigninForm() =
        signInUIState.email.isNotBlank() &&
                signInUIState.password.isNotBlank()

    private fun validateSignupForm() =
        signInUIState.emailSignUp.isNotBlank() &&
                signInUIState.passwordSignUp.isNotBlank() &&
                signInUIState.confirmPasswordSignUp.isNotBlank()
//                &&
//                signInUIState.passwordSignUp == signInUIState.confirmPasswordSignUp


    fun createUser(context: Context) = viewModelScope.launch {
        try {

            if(!validateSignupForm()){
                throw  IllegalArgumentException("Email and password can not be empty")
            }
            signInUIState = signInUIState.copy(isLoading = true)
            if(signInUIState.passwordSignUp !=
                signInUIState.confirmPasswordSignUp){

                throw  IllegalArgumentException("Password do not match")

            }

            signInUIState = signInUIState.copy(signUpError = null)
            repository.createUser(
                signInUIState.emailSignUp,
                signInUIState.passwordSignUp
            ){ isSuccessful ->
                if(isSuccessful){
                    Toast.makeText(context, "تم إنشاء الحساب بنجاح", Toast.LENGTH_SHORT).show()
                    signInUIState = signInUIState.copy(isSuccessful = true)
                }else{
                    Toast.makeText(context, "فشل إنشاء الحساب، حاول مرة أخرى", Toast.LENGTH_SHORT).show()
                    signInUIState = signInUIState.copy(isSuccessful = false)
                }
            }

        }catch (e: Exception){

            signInUIState = signInUIState.copy(signUpError =  e.localizedMessage)
            e.printStackTrace()

        }finally {
            signInUIState = signInUIState.copy(isLoading = false)
        }
    }


    fun signIn(context: Context) = viewModelScope.launch {
        try {

            if(!validateSigninForm()){
                throw  IllegalArgumentException("Email and password can not be empty")
            }
            signInUIState = signInUIState.copy(isLoading = true)
            signInUIState = signInUIState.copy(signInError = null)

            repository.signIn(
                signInUIState.email,
                signInUIState.password
            ){ isSuccessful ->
                if(isSuccessful){
                    signInUIState = signInUIState.copy(isSuccessful = true)
                }else{
                    Toast.makeText(context, "فشل تسجيل الدخول، حاول مرة أخرى", Toast.LENGTH_SHORT).show()
                    signInUIState = signInUIState.copy(isSuccessful = false)
                }
            }

        }catch (e: Exception){

            signInUIState = signInUIState.copy(signInError =  (e.localizedMessage?.plus(" Email: ") ?: "") + signInUIState.email + " Password: " + signInUIState.password)
            e.printStackTrace()

        }finally {
            signInUIState = signInUIState.copy(isLoading = false)
        }
    }

    data class SignInUIState(
        val email: String  = "",
        val password: String  = "",
        val emailSignUp: String  = "",
        val passwordSignUp: String  = "",
        val confirmPasswordSignUp:String = "",
        val isLoading: Boolean = false,
        val isSuccessful: Boolean = false,
        val signInError: String? = null,
        val signUpError: String? = null
        ){


    }
}