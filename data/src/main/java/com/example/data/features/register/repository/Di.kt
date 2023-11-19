package com.example.data.features.register.repository

import com.example.domain.features.register.repository.RegisterRepositoryContract
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class Di {
    @Binds
    abstract fun bindRegisterRepository(
        registerRepositoryImpl: RegisterRepositoryImpl
    ):RegisterRepositoryContract
}