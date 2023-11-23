package com.example.data.features.login.dataSourceImpl

import com.example.data.api.WebServices
import com.example.data.features.login.dataSourceContract.LoginDataSourceContract
import com.example.domain.features.login.model.LoginEntity
import com.example.domain.features.login.model.LoginRequest
import com.example.domain.features.login.model.LoginResponse
import com.example.domain.features.login.model.toEntities
import javax.inject.Inject

class LoginDataSourceImpl @Inject constructor(
    private val webServices: WebServices) :LoginDataSourceContract {
    override suspend fun login(loginRequest: LoginRequest): LoginResponse {
        return webServices.login(loginRequest)
    }
}