package com.example.data.features.login.repositoryImpl

import com.example.data.features.login.dataSourceImpl.LoginDataSourceImpl
import com.example.domain.features.login.model.LoginEntity
import com.example.domain.features.login.model.LoginRequest
import com.example.domain.features.login.model.toEntities
import com.example.domain.features.login.repository.LoginRepositoryContract
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val dataSource:LoginDataSourceImpl
) : LoginRepositoryContract {
    override suspend fun loginRepository(loginRequest: LoginRequest): LoginEntity {
        return dataSource.login(loginRequest).toEntities()

    }
}