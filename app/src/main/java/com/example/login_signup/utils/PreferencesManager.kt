package com.example.login_signup.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys

class PreferencesManager(context: Context) {
    private val sharedPreferences: SharedPreferences by lazy {
        provideEncryptedSharedPreferences(context)
    }

    private fun provideEncryptedSharedPreferences(context: Context): SharedPreferences {

        val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

        return EncryptedSharedPreferences.create(
            "secret_shared_prefs",
            masterKeyAlias,
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    fun storeRememberMePreference(rememberMe: Boolean) {
        with(sharedPreferences.edit()) {
            putBoolean("remember_me", rememberMe)
            apply()
        }
    }

    fun loadRememberMePreference(): Boolean {
        return sharedPreferences.getBoolean("remember_me", false)
    }
}
