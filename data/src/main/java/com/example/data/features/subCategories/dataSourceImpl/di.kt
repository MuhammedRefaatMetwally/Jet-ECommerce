package com.example.data.features.subCategories.dataSourceImpl

import com.example.data.api.WebServices
import com.example.data.features.subCategories.dataSourceContract.SubCategoriesDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object di {

    @Provides
    fun provideSubCategoriesDataSource(
        webServices: WebServices
    ):SubCategoriesDataSource{
        return SubCategoriesDataSourceImpl(webServices)
    }
}