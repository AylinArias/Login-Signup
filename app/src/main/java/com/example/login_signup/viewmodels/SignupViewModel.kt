package com.example.login_signup.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class SignupViewModel : ViewModel() {
    val emailState = mutableStateOf("")
    val phoneState = mutableStateOf("")
    val passwordState = mutableStateOf("")
    val confirmPasswordState = mutableStateOf("")

    val emailErrorState = mutableStateOf<String?>(null)
    val phoneErrorState = mutableStateOf<String?>(null)
    val passwordErrorState = mutableStateOf<String?>(null)
    val confirmPasswordErrorState = mutableStateOf<String?>(null)

    val passwordVisible = mutableStateOf(false)

    fun togglePasswordVisibility() {
        passwordVisible.value = !passwordVisible.value
    }

    fun validateFields(): Boolean {
        val emailPattern = android.util.Patterns.EMAIL_ADDRESS
        val phonePattern = Regex("\\+\\d{1,3} \\d{6,15}")

        var isValid = true

        emailErrorState.value =
            if (emailState.value.isEmpty() || !emailPattern.matcher(emailState.value).matches()) {
                isValid = false
                "Correo electrónico incorrecto"
            } else {
                null
            }

        phoneErrorState.value =
            if (phoneState.value.isEmpty() || !phonePattern.matches(phoneState.value)) {
                isValid = false
                "Número de teléfono incorrecto"
            } else {
                null
            }

        passwordErrorState.value = if (passwordState.value.isEmpty()) {
            isValid = false
            "Contraseña vacía"
        } else {
            null
        }

        confirmPasswordErrorState.value = if (passwordState.value != confirmPasswordState.value) {
            isValid = false
            "Las contraseñas no coinciden"
        } else {
            null
        }

        return isValid
    }
}
