package com.example.data.features.product.dataSourceContract

import androidx.paging.PagingData
import com.example.domain.common.ResultWrapper
import com.example.domain.features.products.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductDataSource {
<<<<<<<<< Temporary merge branch 1
    suspend fun getProducts(categoryId: String? = null): Flow<ResultWrapper<List<Product>>>
    suspend fun getProductsPaging(categoryId: String? = null): Flow<PagingData<Product>>
=========
    suspend fun getProducts() : Flow<ResultWrapper<List<Product?>?>>
>>>>>>>>> Temporary merge branch 2
}