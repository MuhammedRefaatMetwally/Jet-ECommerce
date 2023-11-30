package com.example.domain.features.login.model


import com.google.gson.annotations.SerializedName


data  class LoginResponse(
    @field:SerializedName("message")
    val message: String? = null,
    @field:SerializedName("user")
    val user: UserEntity?=null,
    @field:SerializedName("token")
    val token:String?=null

)

data class UserEntity(
    @field:SerializedName("role")
    val role:String?=null,
    @field:SerializedName("name")
    val name:String?=null,
    @field:SerializedName("email")
    val email:String?=null
)
