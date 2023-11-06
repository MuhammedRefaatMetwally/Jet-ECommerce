package com.example.domain.features.product.repository

import androidx.paging.PagingData
import com.example.domain.common.ResultWrapper
import com.example.domain.features.product.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    suspend fun getProducts(categoryId: String? = null): Flow<ResultWrapper<List<Product>>>

    suspend fun getProductsPaging(categoryId: String? = null): Flow<PagingData<Product>>
}