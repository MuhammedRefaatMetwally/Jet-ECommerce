package com.example.data.features.product.dataSourceContract

import com.example.domain.common.ResultWrapper
import com.example.domain.features.products.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductDataSource {
    suspend fun getProducts() : Flow<ResultWrapper<List<Product?>?>>
}