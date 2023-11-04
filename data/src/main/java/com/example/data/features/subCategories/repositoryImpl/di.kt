package com.example.data.features.subCategories.repositoryImpl

import com.example.data.features.subCategories.dataSourceContract.SubCategoriesDataSource
import com.example.domain.features.subCategories.repository.SubCategoryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object di {

    @Provides
    fun provideSubCategoriesRepository(
        subCategoriesDataSource: SubCategoriesDataSource
    ):SubCategoryRepository{
        return SubCategoriesRepositoryImpl(subCategoriesDataSource)
    }
}