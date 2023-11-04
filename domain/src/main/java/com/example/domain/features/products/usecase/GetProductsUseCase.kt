package com.example.domain.features.products.usecase

import com.example.domain.common.ResultWrapper
import com.example.domain.features.products.model.Product
import com.example.domain.features.products.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private  val repository: ProductRepository
) {
    suspend fun invoke(): Flow<ResultWrapper<List<Product?>?>> {
        return repository.getProducts()
    }


}