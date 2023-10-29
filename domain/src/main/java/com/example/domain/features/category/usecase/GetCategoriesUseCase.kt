package com.example.domain.features.category.usecase

import com.example.domain.common.ResultWrapper
import com.example.domain.features.category.model.Category
import com.example.domain.features.category.repository.CategoriesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private  val repository: CategoriesRepository
) {
     suspend fun invoke(): Flow<ResultWrapper<List<Category?>?>> {
        return repository.getAllCategories()
    }
}