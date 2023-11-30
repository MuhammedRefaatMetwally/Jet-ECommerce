package com.example.data.features.login.repositoryImpl

import com.example.data.features.login.dataSourceImpl.LoginDataSourceImpl

import com.example.domain.common.ResultWrapper
import com.example.domain.features.login.model.LoginRequest
import com.example.domain.features.login.model.LoginResponse
import com.example.domain.features.login.repository.LoginRepositoryContract
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val dataSource:LoginDataSourceImpl
) : LoginRepositoryContract {
    override suspend fun loginRepository(loginRequest: LoginRequest): LoginResponse {
        return dataSource.login(loginRequest)

    }
}