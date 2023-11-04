package com.example.domain.features.subCategories.usecase

import com.example.domain.common.ResultWrapper
import com.example.domain.features.subCategories.model.SubCategory
import com.example.domain.features.subCategories.repository.SubCategoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSubCategoriesOnCategoryUseCase @Inject constructor(
    private val repo:SubCategoryRepository
) {
    suspend operator fun invoke(categoryId:String):Flow<ResultWrapper<List<SubCategory>>>{
        return  repo.getSubCategoriesOnCategory(categoryId)
    }
}