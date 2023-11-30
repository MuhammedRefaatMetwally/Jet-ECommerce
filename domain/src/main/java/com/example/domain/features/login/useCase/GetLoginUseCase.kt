package com.example.domain.features.login.useCase

import com.example.domain.common.ResultWrapper
import com.example.domain.features.login.model.LoginEntity
import com.example.domain.features.login.model.LoginRequest
import com.example.domain.features.login.model.LoginResponse
import com.example.domain.features.login.repository.LoginRepositoryContract
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLoginUseCase @Inject constructor(
  private val loginRepositoryContract: LoginRepositoryContract
) {

    suspend operator fun  invoke(loginRequest: LoginRequest): LoginResponse {
    return loginRepositoryContract.loginRepository(loginRequest)
}
}