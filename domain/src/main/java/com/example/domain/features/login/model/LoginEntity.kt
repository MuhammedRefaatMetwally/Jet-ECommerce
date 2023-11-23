package com.example.domain.features.login.model

data class LoginEntity(
    val message: String? = null,
    val user:UserEntity?=null,
    val token:String?=null

)
data class UserEntity(
    val role:String?=null,
    val name:String?=null,
    val email:String?=null
)
