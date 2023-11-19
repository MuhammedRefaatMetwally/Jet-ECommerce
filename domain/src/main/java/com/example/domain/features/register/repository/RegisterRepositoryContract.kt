package com.example.domain.features.register.repository


import com.example.domain.features.register.model.RegisterEntity
import com.example.domain.features.register.model.RegisterRequest

interface RegisterRepositoryContract {
    suspend fun registration(registerRequest: RegisterRequest): RegisterEntity
}