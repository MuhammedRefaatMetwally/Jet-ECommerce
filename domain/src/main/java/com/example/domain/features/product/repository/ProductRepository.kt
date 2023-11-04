package com.example.domain.features.product.repository

import com.example.domain.common.ResultWrapper
import com.example.domain.features.product.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    suspend fun  getProducts() : Flow<ResultWrapper<List<Product?>?>>
}