package com.example.data.features.categories.repository

import com.example.data.features.categories.dataSourceContract.CategoryDataSource
import com.example.domain.common.ResultWrapper
import com.example.domain.features.category.model.Category
import com.example.domain.features.category.repository.CategoriesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CategoriesRepositoryImpl @Inject constructor(
    private val dataSource: CategoryDataSource
) : CategoriesRepository {
    override suspend fun getAllCategories(page: Int): Flow<ResultWrapper<List<Category?>?>> {
        return dataSource.getCategories()
    }
}