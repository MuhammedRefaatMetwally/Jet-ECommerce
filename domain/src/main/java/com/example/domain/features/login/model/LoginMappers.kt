package com.example.domain.features.login.model

fun LoginResponse.toEntities():LoginEntity{
    return LoginEntity(message,user?.toEntity(),token)
}
fun UserModel.toEntity():UserEntity {
    return UserEntity(role,name,email)
}