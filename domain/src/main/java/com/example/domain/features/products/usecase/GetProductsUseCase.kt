package com.example.domain.features.products.usecase

import com.example.domain.common.ResultWrapper
import com.example.domain.features.products.model.Product
import com.example.domain.features.products.repository.ProductsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val repository: ProductsRepository
) {
    suspend fun invoke(categoryId: String? = null): Flow<ResultWrapper<List<Product?>?>> {
        return repository.getProducts(categoryId)
    }


}