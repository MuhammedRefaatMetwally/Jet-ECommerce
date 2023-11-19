package com.example.data.features.register.repository

import com.example.data.features.register.dataSourceImpl.RegisterDataSourceImpl
import com.example.domain.features.register.model.toEntity
import com.example.domain.features.register.model.RegisterEntity
import com.example.domain.features.register.model.RegisterRequest
import com.example.domain.features.register.repository.RegisterRepositoryContract
import javax.inject.Inject

class RegisterRepositoryImpl @Inject constructor(
    private val dataSource :RegisterDataSourceImpl) : RegisterRepositoryContract {
    override suspend fun registration(registerRequest: RegisterRequest): RegisterEntity {
        return dataSource.registration(registerRequest).toEntity()
    }
}