package com.example.domain.features.login.useCase

import com.example.domain.features.login.model.LoginEntity
import com.example.domain.features.login.model.LoginRequest
import com.example.domain.features.login.repository.LoginRepositoryContract
import javax.inject.Inject

class GetLoginUseCase @Inject constructor(
  private val loginRepositoryContract: LoginRepositoryContract
) {

    suspend operator fun  invoke(loginRequest: LoginRequest):LoginEntity{
    return loginRepositoryContract.loginRepository(loginRequest)
}
}