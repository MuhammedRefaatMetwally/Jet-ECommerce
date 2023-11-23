package com.example.domain.features.login.repository

import com.example.domain.features.login.model.LoginEntity
import com.example.domain.features.login.model.LoginRequest

interface LoginRepositoryContract {
    suspend fun loginRepository(loginRequest: LoginRequest):LoginEntity
}