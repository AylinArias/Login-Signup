package com.example.login_signup.di

import com.example.login_signup.utils.PreferencesManager
import com.example.login_signup.viewmodels.LoginViewModel
import com.example.login_signup.viewmodels.SignupViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { PreferencesManager(get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { SignupViewModel() }
}