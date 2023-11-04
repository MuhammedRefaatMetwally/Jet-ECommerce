package com.example.data.features.product.datasourceImpl

import com.example.data.api.WebServices
import com.example.data.features.product.datasourceContract.ProductDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object di {
    @Provides
    fun provideProductDataSource(
        webServices: WebServices
    ):ProductDataSource{
        return ProductDataSourceImpl(webServices)
    }
}