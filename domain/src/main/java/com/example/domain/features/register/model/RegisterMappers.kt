package com.example.domain.features.register.model


// Extension Functions
fun RegisterResponse.toEntity(): RegisterEntity {
    return RegisterEntity(message,userModel?.toEntity(),token,statusMsg)
}
fun UserModel.toEntity(): UserEntity {
    return UserEntity(role, name, email)

}