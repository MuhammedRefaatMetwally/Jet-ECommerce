package com.example.data.features.product.dataSourceContract

import com.example.domain.common.ResultWrapper
import com.example.domain.features.product.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductDataSource {
    suspend fun getProducts(categoryId: String? = null): Flow<ResultWrapper<List<Product>>>
}