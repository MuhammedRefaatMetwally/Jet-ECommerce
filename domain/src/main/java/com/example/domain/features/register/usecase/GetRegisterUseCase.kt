package com.example.domain.features.register.usecase

import com.example.domain.features.register.model.RegisterEntity
import com.example.domain.features.register.model.RegisterRequest
import com.example.domain.features.register.repository.RegisterRepositoryContract
import javax.inject.Inject

class GetRegisterUseCase @Inject constructor(
   private val repositoryContract: RegisterRepositoryContract) {
    suspend operator fun invoke(registerRequest: RegisterRequest): RegisterEntity {
        return repositoryContract.registration(registerRequest)
    }
}