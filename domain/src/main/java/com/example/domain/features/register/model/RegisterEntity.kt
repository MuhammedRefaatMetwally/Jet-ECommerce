package com.example.domain.features.register.model

data class RegisterEntity(

    val message: String? = null,
    val user: UserEntity? = null,
    val token: String? = null,
    val statusMsg: String? = null
)

data class UserEntity(

    val role: String? = null,
    val name: String? = null,
    val email: String? = null
)
