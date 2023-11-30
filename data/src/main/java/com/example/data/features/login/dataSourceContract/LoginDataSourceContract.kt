package com.example.data.features.login.dataSourceContract


import com.example.domain.common.ResultWrapper
import com.example.domain.features.login.model.LoginRequest
import com.example.domain.features.login.model.LoginResponse
import kotlinx.coroutines.flow.Flow

interface LoginDataSourceContract {
    suspend fun login(loginRequest: LoginRequest):LoginResponse
}