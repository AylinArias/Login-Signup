package com.example.login_signup.utils

import android.util.Patterns

object ValidationUtils {
    fun validateEmail(email: String): String {
        return when {
            email.isEmpty() -> "Correo electrónico requerido"
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> "Correo electrónico inválido"
            else -> ""
        }
    }

    fun validatePassword(password: String): String {
        return when {
            password.isEmpty() -> "Contraseña requerida"
            password.length < 8 -> "La contraseña debe tener al menos 8 caracteres"
            else -> ""
        }
    }
}
