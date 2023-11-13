package com.example.domain.features.products.repository

import androidx.paging.PagingData
import com.example.domain.common.ResultWrapper
import com.example.domain.features.products.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {
    suspend fun getProducts(categoryId: String? = null): Flow<ResultWrapper<List<Product?>?>>

    suspend fun getProductsPaging(categoryId: String? = null): Flow<PagingData<Product>>
    suspend fun getSpecificProduct(productId:String):Flow<ResultWrapper<Product>>
}