package com.example.data.features.login.repositoryImpl

import com.example.data.features.login.dataSourceContract.LoginDataSourceContract
import com.example.data.features.login.dataSourceImpl.LoginDataSourceImpl
import com.example.domain.features.login.repository.LoginRepositoryContract
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
abstract class Di {
    @Binds
    abstract fun bindRepositoryImpl(
        dataSourceImpl: LoginRepositoryImpl
    ):LoginRepositoryContract
}