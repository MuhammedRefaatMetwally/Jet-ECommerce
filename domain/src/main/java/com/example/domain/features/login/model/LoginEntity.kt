package com.example.domain.features.login.model


data class LoginEntity(

    val message: String? = null,

    val user: UserEntity2?=null,

    val token:String?=null

)

data class UserEntity2(

    val role:String?=null,

    val name:String?=null,

    val email:String?=null
)
