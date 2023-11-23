package com.example.data.features.login.dataSourceImpl

import com.example.data.features.login.dataSourceContract.LoginDataSourceContract
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class Di {
    @Binds
    abstract fun bindDataSourceImpl(
        dataSourceImpl: LoginDataSourceImpl):LoginDataSourceContract
}