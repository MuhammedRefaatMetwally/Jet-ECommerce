package com.example.data.features.products.dataSourceContract

import androidx.paging.PagingData
import com.example.domain.common.ResultWrapper
import com.example.domain.features.products.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductDataSource {
    suspend fun getProducts(categoryId: String? = null): Flow<ResultWrapper<List<Product?>?>>
    suspend fun getProductsPaging(categoryId: String? = null): Flow<PagingData<Product>>
}