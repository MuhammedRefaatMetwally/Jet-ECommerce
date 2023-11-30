package com.example.domain.features.login.repository


import com.example.domain.common.ResultWrapper
import com.example.domain.features.login.model.LoginEntity
import com.example.domain.features.login.model.LoginRequest
import com.example.domain.features.login.model.LoginResponse
import kotlinx.coroutines.flow.Flow

interface LoginRepositoryContract {
    suspend fun loginRepository(loginRequest:LoginRequest):LoginResponse
}