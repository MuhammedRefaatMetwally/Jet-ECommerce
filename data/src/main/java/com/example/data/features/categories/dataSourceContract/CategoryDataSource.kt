package com.example.data.features.categories.dataSourceContract

import com.example.domain.common.ResultWrapper
import com.example.domain.features.category.model.Category
import kotlinx.coroutines.flow.Flow

interface CategoryDataSource {
    suspend fun getCategories(page: Int = 1): Flow<ResultWrapper<List<Category?>?>>
}