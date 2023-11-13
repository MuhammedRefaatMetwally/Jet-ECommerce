package com.example.domain.features.products.usecase

import com.example.domain.common.ResultWrapper
import com.example.domain.features.products.model.Product
import com.example.domain.features.products.repository.ProductsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSpecificProductUseCase @Inject constructor(
    private val repo : ProductsRepository
) {
    suspend operator fun invoke(productId:String):Flow<ResultWrapper<Product>>{
        return repo.getSpecificProduct(productId)
    }
}