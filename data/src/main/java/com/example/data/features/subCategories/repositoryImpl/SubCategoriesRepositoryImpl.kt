package com.example.data.features.subCategories.repositoryImpl

import com.example.data.features.subCategories.dataSourceContract.SubCategoriesDataSource
import com.example.domain.common.ResultWrapper
import com.example.domain.features.subCategories.model.SubCategory
import com.example.domain.features.subCategories.repository.SubCategoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SubCategoriesRepositoryImpl @Inject constructor(
    private val subCategoriesDataSource: SubCategoriesDataSource
):SubCategoryRepository {
    override suspend fun getSubCategoriesOnCategory(categoryId: String): Flow<ResultWrapper<List<SubCategory>>> {
       return subCategoriesDataSource.getSubCategoriesOnCateGory(categoryId)
    }
}