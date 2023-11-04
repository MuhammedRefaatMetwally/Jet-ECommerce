package com.example.domain.features.product.usecase

import com.example.domain.common.ResultWrapper
import com.example.domain.features.product.model.Product
import com.example.domain.features.product.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val repo :ProductRepository
) {
    suspend operator fun invoke(categoryId:String?=null) : Flow<ResultWrapper<List<Product>>>{
      return  repo.getProducts(categoryId)
    }
}