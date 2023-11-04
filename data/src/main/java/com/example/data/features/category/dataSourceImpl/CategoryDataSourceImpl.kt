package com.example.data.features.category.dataSourceImpl

import com.example.data.api.WebServices
import com.example.data.features.category.dataSourceContract.CategoryDataSource
import com.example.data.safeAPiCall
import com.example.domain.common.ResultWrapper
import com.example.domain.features.category.model.Category
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CategoryDataSourceImpl @Inject constructor(
    private val webServices: WebServices
) : CategoryDataSource {
    override suspend fun getCategories(page: Int): Flow<ResultWrapper<List<Category?>?>> {
        return safeAPiCall { webServices.getCategories() }
    }
}