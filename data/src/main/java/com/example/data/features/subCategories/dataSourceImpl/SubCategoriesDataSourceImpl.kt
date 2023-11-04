package com.example.data.features.subCategories.dataSourceImpl

import com.example.data.api.WebServices
import com.example.data.features.subCategories.dataSourceContract.SubCategoriesDataSource
import com.example.data.safeAPiCall
import com.example.domain.common.ResultWrapper
import com.example.domain.features.subCategories.model.SubCategory
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SubCategoriesDataSourceImpl @Inject constructor(
    private val webServices: WebServices
) : SubCategoriesDataSource {
    override suspend fun getSubCategoriesOnCateGory(categoryId: String): Flow<ResultWrapper<List<SubCategory>>> {
        return safeAPiCall { webServices.getSubCategoriesOnCategory(categoryId) }
    }
}