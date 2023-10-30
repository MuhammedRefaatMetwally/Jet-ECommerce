package com.example.jet_ecommerce.ui.viewModels.loginViewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class RegisterViewModel:ViewModel() {
    val emailState= mutableStateOf("")
    val emailError = mutableStateOf("")

    val passwordState = mutableStateOf("")
    val passwordError = mutableStateOf("")

}