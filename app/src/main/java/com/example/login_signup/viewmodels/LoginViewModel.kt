package com.example.login_signup.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.login_signup.utils.PreferencesManager
import com.example.login_signup.utils.ValidationUtils

class LoginViewModel(val preferencesManager: PreferencesManager) : ViewModel() {
    var emailState = mutableStateOf("")
    var passwordState = mutableStateOf("")
    var passwordVisible = mutableStateOf(false)
    var emailError = mutableStateOf("")
    var passwordError = mutableStateOf("")
    var rememberMeState = mutableStateOf(false)

    init {
        rememberMeState.value = preferencesManager.loadRememberMePreference()
    }

    fun validateFields() {
        emailError.value = ValidationUtils.validateEmail(emailState.value)
        passwordError.value = ValidationUtils.validatePassword(passwordState.value)
    }

    fun toggleRememberMe() {
        rememberMeState.value = !rememberMeState.value
        preferencesManager.storeRememberMePreference(rememberMeState.value)
    }
}
