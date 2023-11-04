package com.example.data.features.product.repositoryImpl

import com.example.data.features.product.datasourceContract.ProductDataSource
import com.example.domain.common.ResultWrapper
import com.example.domain.features.product.model.Product
import com.example.domain.features.product.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val productDataSource: ProductDataSource
):ProductRepository {
    override suspend fun getProducts(categoryId: String?): Flow<ResultWrapper<List<Product>>> {
        return productDataSource.getProducts(categoryId)
    }
}