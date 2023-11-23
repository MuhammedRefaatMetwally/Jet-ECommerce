package com.example.data.features.login.dataSourceContract

import com.example.domain.features.login.model.LoginRequest
import com.example.domain.features.login.model.LoginResponse

interface LoginDataSourceContract {
    suspend fun login(loginRequest: LoginRequest):LoginResponse
}