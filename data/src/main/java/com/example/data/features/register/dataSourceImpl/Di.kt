package com.example.data.features.register.dataSourceImpl

import com.example.data.features.register.dataSourceContract.RegisterDataSourceContract
import com.example.data.features.register.repository.RegisterRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
abstract class Di {

    @Binds
    abstract fun bindRegisterDataSource(
        registerDataSourceImpl: RegisterDataSourceImpl
    ):RegisterDataSourceContract
}