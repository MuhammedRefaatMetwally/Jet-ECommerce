package com.example.domain.features.category.repository

import com.example.domain.common.ResultWrapper
import com.example.domain.features.category.model.Category
import kotlinx.coroutines.flow.Flow

interface CategoriesRepository {
    suspend fun getAllCategories(page : Int = 1) : Flow<ResultWrapper<List<Category?>?>>
}