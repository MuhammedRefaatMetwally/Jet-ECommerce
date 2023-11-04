package com.example.data.features.subCategories.dataSourceContract

import com.example.domain.common.ResultWrapper
import com.example.domain.features.subCategories.model.SubCategory
import kotlinx.coroutines.flow.Flow

interface SubCategoriesDataSource{
    suspend fun getSubCategoriesOnCateGory(categoryId:String):Flow<ResultWrapper<List<SubCategory>>>
}