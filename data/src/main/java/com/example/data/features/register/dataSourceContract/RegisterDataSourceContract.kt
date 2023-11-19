package com.example.data.features.register.dataSourceContract

import com.example.domain.features.register.model.RegisterEntity
import com.example.domain.features.register.model.RegisterRequest
import com.example.domain.features.register.model.RegisterResponse

interface RegisterDataSourceContract {
    suspend fun registration(registerRequest: RegisterRequest): RegisterResponse
}

