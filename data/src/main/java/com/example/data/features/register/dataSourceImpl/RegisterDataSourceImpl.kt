package com.example.data.features.register.dataSourceImpl

import com.example.data.api.WebServices
import com.example.data.features.register.dataSourceContract.RegisterDataSourceContract
import com.example.domain.features.register.model.RegisterEntity
import com.example.domain.features.register.model.RegisterRequest
import com.example.domain.features.register.model.RegisterResponse
import javax.inject.Inject

class RegisterDataSourceImpl @Inject constructor(
    private val webServices: WebServices
) : RegisterDataSourceContract {
    override suspend fun registration(registerRequest: RegisterRequest): RegisterResponse {
       return webServices.registration(registerRequest)
    }
}
