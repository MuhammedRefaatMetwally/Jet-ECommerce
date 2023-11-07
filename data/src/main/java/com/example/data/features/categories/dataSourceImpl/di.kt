package com.example.data.features.categories.dataSourceImpl

import com.example.data.features.categories.dataSourceContract.CategoryDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class di {

    @Binds
    abstract fun bindCategoryDataSource(
        categoryDataSourceImpl: CategoryDataSourceImpl
    ) : CategoryDataSource
}