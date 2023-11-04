package com.example.domain.features.subCategories.repository

import com.example.domain.common.ResultWrapper
import com.example.domain.features.subCategories.model.SubCategory
import kotlinx.coroutines.flow.Flow

interface SubCategoryRepository {
    suspend fun getSubCategoriesOnCategory(categoryId:String): Flow<ResultWrapper<List<SubCategory>>>
}

