package com.example.data.features.categories.repository

import com.example.domain.features.category.repository.CategoriesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class di {
    @Binds
    abstract fun bindCategoriesRepository(
        repo: CategoriesRepositoryImpl
    ) : CategoriesRepository
}